package org.yq.tool.yktools.action;

import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.yq.tool.yktools.entity.HCHisDoctorEntity;
import org.yq.tool.yktools.repository.HCHisDoctorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
                else if (StringUtils.contains(RegExUtils.removeAll(ele.getName().trim(),"　|\\s"),"急诊"))
                {
                    //System.out.println(ele.getName());
                }
                else if (StringUtils.contains(RegExUtils.removeAll(ele.getName().trim(),"　|\\s"),"普通"))
                {
                    //System.out.println(ele.getName());
                }
                else if (StringUtils.contains(RegExUtils.removeAll(ele.getName().trim(),"　|\\s"),"门诊"))
                {
                    //System.out.println(ele.getName());
                }
                else if (StringUtils.contains(RegExUtils.removeAll(ele.getName().trim(),"　|\\s"),"专科"))
                {
                    //System.out.println(ele.getName());
                }
                else if (StringUtils.contains(RegExUtils.removeAll(ele.getName().trim(),"　|\\s"),"医学中心"))
                {
                    //System.out.println(ele.getName());
                }
                else if (StringUtils.contains(RegExUtils.removeAll(ele.getName().trim(),"　|\\s"),"特诊"))
                {
                    //System.out.println(ele.getName());
                }
                else if (StringUtils.containsAny(RegExUtils.removeAll(ele.getName().trim(),"　|\\s"),"本院号","义诊号","教授号"))
                {
                    //System.out.println(ele.getName());
                }
                else if (StringUtils.containsAny(RegExUtils.removeAll(ele.getName().trim(),"　|\\s"),"特惠号","老年号","夜诊号"))
                {
                    //System.out.println(ele.getName());
                }
                else
                {
                    doctors.add(ele);
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
        doctors.forEach(ele -> {
            System.out.println(ele.getName());
        });



    }

    public static void main1(String[] args) {
        String s =  "李　萍";


        System.out.println(RegExUtils.removeAll(s,"　|\\s"));
        System.out.println(!CHINESE_XINJIANG_PATTERN.matcher(s.trim().replaceAll("　|\\s", "")).matches());
    }
}
