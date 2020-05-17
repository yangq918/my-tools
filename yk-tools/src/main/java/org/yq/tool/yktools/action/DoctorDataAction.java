package org.yq.tool.yktools.action;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.yq.tool.yktools.entity.dev.HCHisDoctorEntity;
import org.yq.tool.yktools.repository.dev.HCHisDoctorRepository;
import org.yq.tool.yktools.utils.DoctorNameExclu;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

@Component
public class DoctorDataAction {
    @Autowired
    private  HCHisDoctorRepository hcHisDoctorRepository;

    private static final Pattern CHINESE_XINJIANG_PATTERN =
            Pattern.compile("^[\u4e00-\u9fa5.·\u36c3\u4DAE]{0,}$");




    public void doAction()
    {
        long total = hcHisDoctorRepository.count();
        System.out.println(total);
        int i = 0;
        List<HCHisDoctorEntity> doctors = new ArrayList<>();
        List<String> names = new ArrayList<>();
        Set<String> exSet = new LinkedHashSet<>();
        do {
            Page<HCHisDoctorEntity> page = hcHisDoctorRepository.findAll(PageRequest.of(i,5000));
            page.forEach(ele-> {
                if(Objects.equals(ele.getName(),ele.getTitle()))
                {
                    //System.out.println(ele.getName());
                }
                else if(Objects.isNull(ele.getName())||ele.getName().trim().length()<=0)
                {
                    //System.out.println(ele.getName());
                }
                else if(!CHINESE_XINJIANG_PATTERN.matcher(RegExUtils.removeAll(ele.getName().trim(),"　|\\s")).matches()) {
                    //System.out.println(ele.getName());
                }
                else if (StringUtils.containsAny(RegExUtils.removeAll(ele.getName().trim(),"　|\\s"), DoctorNameExclu.EXSTR))
                {
                    exSet.add(ele.getName());
                    //System.out.println(ele.getName());
                }
                else if(RegExUtils.removeAll(ele.getName().trim(),"　|\\s").length()>=4)
                {
                    System.out.println(ele.getName());
                }
                else
                {
                    doctors.add(ele);
                    names.add(ele.getName());
                }

            });
            if(page.isLast())
            {
                break;
            }
            else
            {
                i = i+1;
            }
        }
        while (true);

        System.out.println(doctors.size());
        //writeToFile(names);

        //doctors.forEach(ele -> {
        //    System.out.println(ele.getName());
        //});

        //exSet.forEach(ele -> {
            //System.out.println(ele);
        //});



    }

    private void writeToFile(List<String> names) {
        try {
            FileUtils.writeLines(new File("F:\\tmp\\doctor.txt"),names);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main1(String[] args) {
        String s =  "李　萍";


        System.out.println(RegExUtils.removeAll(s,"　|\\s"));
        System.out.println(!CHINESE_XINJIANG_PATTERN.matcher(s.trim().replaceAll("　|\\s", "")).matches());
    }
}
