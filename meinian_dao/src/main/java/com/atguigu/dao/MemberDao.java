package com.atguigu.dao;

import com.atguigu.pojo.Member;

import java.util.List;
import java.util.Map;

/**
 * @author XuKe   Email:xuke598654158@126.com
 * @Description
 * @date 2021/1/27  15:53
 */
public interface MemberDao {
    Member getMemberByTelephone(String telephone);

    void add(Member member);

    Member getMemberById(Integer memberId);

    int findMemberCountBeforeDate(String regTime);


    int getTodayNewMember(String date);
    int getTotalMember();
    int getThisWeekAndMonthNewMember(String date);
}
