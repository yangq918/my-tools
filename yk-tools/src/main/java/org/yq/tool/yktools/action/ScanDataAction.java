package org.yq.tool.yktools.action;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;
import org.yq.tool.yktools.entity.dev.HCHisDeptEntity;
import org.yq.tool.yktools.entity.dev.HCHisDeptKey;
import org.yq.tool.yktools.entity.dev.HCHisDoctorEntity;
import org.yq.tool.yktools.entity.dev.HCHisEntity;
import org.yq.tool.yktools.entity.online.OPHisEntity;
import org.yq.tool.yktools.repository.dev.HCDeptRepository;
import org.yq.tool.yktools.repository.dev.HCHisDoctorRepository;
import org.yq.tool.yktools.repository.dev.HCHisRepository;
import org.yq.tool.yktools.repository.online.OPHisRepository;
import org.yq.tool.yktools.utils.DoctorNameExclu;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class ScanDataAction {

    @Autowired
    private HCHisRepository hcHisRepository;

    @Autowired
    private HCDeptRepository hcDeptRepository;

    @Autowired
    private HCHisDoctorRepository hcHisDoctorRepository;

    @Autowired
    private OPHisRepository opHisRepository;

    private boolean insertFlag = false;

    private static final Pattern CHINESE_XINJIANG_PATTERN =
            Pattern.compile("^[\u4e00-\u9fa5.·\u36c3\u4DAE]{0,}$");


    public void doAction() {

        List<HCHisEntity> hises = hcHisRepository.findAll();

        List<String> names = new ArrayList<>();
        hises.forEach(ele -> {

            //System.out.println(ele.getName());
            HCHisDeptEntity deptEntity = new HCHisDeptEntity();
            HCHisDeptKey deptKey = new HCHisDeptKey();
            deptKey.setHisId(ele.getId());
            deptEntity.setDeptKey(deptKey);
            List<HCHisDeptEntity> depts = hcDeptRepository.findAll(Example.of(deptEntity));
            ele.setDepts(depts);
            HCHisDoctorEntity queryDoctor = new HCHisDoctorEntity();
            queryDoctor.setHisId(ele.getId());

            OPHisEntity opHisEntity = initOPHis(ele);

            List<HCHisDoctorEntity> doctorEntities = hcHisDoctorRepository.findAll(Example.of(queryDoctor));
            doctorEntities = filterSameName(doctorEntities);
            Map<String, List<HCHisDoctorEntity>> doctorMap = doctorEntities.stream().collect(Collectors.groupingBy(d -> d.getDeptNo()));

            if (null != depts) {
                depts.forEach(dept -> {
                    //System.out.println(dept.getName());
                    List<HCHisDoctorEntity> doctors = doctorMap.get(dept.getDeptKey().getNo());
                    dept.setDoctors(doctors);
                    if (doctors != null) {
                        doctors.forEach(doctor -> {


                            if (isDoctor(doctor)) {
                                names.add(doctor.getName());
                            }
                        });
                    }
                });
            }
            System.out.println(names.size());
        });

        writeToFile(names);

    }

    private OPHisEntity initOPHis(HCHisEntity ele) {
        OPHisEntity opHisEntity = opHisRepository.findByName(ele.getName().trim());
        if(null==opHisEntity)
        {
            Long maxHisId =  opHisRepository.findMaxHisId();
            opHisEntity = new OPHisEntity();
            opHisEntity.setHisId(maxHisId+1);
            opHisEntity.setHisLogo(ele.getLogoUrl());
            opHisEntity.setName(ele.getName());
            opHisEntity.setOrigin(1);
            opHisRepository.saveAndFlush(opHisEntity);
        }
        return opHisEntity;
    }

    private List<HCHisDoctorEntity> filterSameName(List<HCHisDoctorEntity> doctorEntities) {
        if (null == doctorEntities) {
            doctorEntities = new ArrayList<>();
        }

        doctorEntities
                .stream()
                .filter(Objects::nonNull)
                .filter(doctorEntity -> Objects.nonNull(doctorEntity.getName()))
                .collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing((doctor) -> RegExUtils.removeAll(doctor.getName().trim(), "　|\\s")))), ArrayList::new));

        return doctorEntities;
    }


    private void writeToFile(List<String> names) {
        try {
            FileUtils.writeLines(new File("G:\\tmp\\doctor.txt"), names);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isDoctor(HCHisDoctorEntity ele) {

        if (Objects.equals(ele.getName(), ele.getTitle())) {
            //System.out.println(ele.getName());
        } else if (Objects.isNull(ele.getName()) || ele.getName().trim().length() <= 0) {
            //System.out.println(ele.getName());
        } else if (!CHINESE_XINJIANG_PATTERN.matcher(RegExUtils.removeAll(ele.getName().trim(), "　|\\s")).matches()) {
            //System.out.println(ele.getName());
        } else if (StringUtils.containsAny(RegExUtils.removeAll(ele.getName().trim(), "　|\\s"), DoctorNameExclu.EXSTR)) {
            //System.out.println(ele.getName());
        } else {
            return true;
        }
        return false;
    }
}
