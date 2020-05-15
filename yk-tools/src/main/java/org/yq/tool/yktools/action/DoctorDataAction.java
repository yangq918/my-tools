package org.yq.tool.yktools.action;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RegExUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.yq.tool.yktools.entity.HCHisDoctorEntity;
import org.yq.tool.yktools.repository.HCHisDoctorRepository;

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

    private static final String[] exStr = new String[]{"科",
            "主任",
            "诊",
            "号",
            "医",
            "普通",
            "咨询",
            "病",
            "遗传",
            "优生",
            "大学城导管",
            "大学城管道",
            "二沙导管",
            "测试",
            "团队",
            "管理",
            "检查",
            "出生证现场",
            "出生",
            "耳鼻喉",
            "专家",
            "中心",
            "障碍",
            "康复",
            "室",
            "证明",
            "正畸",
            "种植",
            "口外",
            "烟大",
            "儿牙",
            "修复",
            "手术",
            "预约",
            "本院",
            "主治",
            "五保",
            "导管",
            "美容",
            "整形",
            "小儿推拿",
            "免疫",
            "护理",
            "职工",
            "大学城",
            "下午",
            "造口",
            "伤口",
            "治疗",
            "鉴定",
            "司法",
            "复查",
            "保健",
            "催乳",
            "产后",
            "用户",
            "肿瘤",
            "筛查",
            "综合",
            "牙髓",
            "处方",
            "肿瘤",
            "脊柱",
            "创面",
            "胸外",
            "神内",
            "神经内",
            "工程师",
            "伤研究所",
            "合理用药",
            "检验",
            "治疗",
            "体检",
            "农合",
            "保健",
            "推拿",
            "打印",
            "核磁共振",
            "服务",
            "接种",
            "筛查",
            "套餐",
            "单人",
            "双人",
            "经济型",
            "标准型",
            "妊娠",
            "瘦身",
            "免费",
            "减肥",
            "色痣",
            "阴道紧缩",
            "移植",
            "点痣",
            "彩超",
            "无针水光"

    };


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
                else if (Objects.isNull(ele.getHis()))
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
                else if (StringUtils.containsAny(RegExUtils.removeAll(ele.getName().trim(),"　|\\s"),exStr))
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
        try {
            FileUtils.writeLines(new File("F:\\tmp\\doctor.txt"),names);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //doctors.forEach(ele -> {
        //    System.out.println(ele.getName());
        //});

        //exSet.forEach(ele -> {
            //System.out.println(ele);
        //});



    }

    public static void main1(String[] args) {
        String s =  "李　萍";


        System.out.println(RegExUtils.removeAll(s,"　|\\s"));
        System.out.println(!CHINESE_XINJIANG_PATTERN.matcher(s.trim().replaceAll("　|\\s", "")).matches());
    }
}
