package com.mao.design.design.principle.demeter;


/**
 * @author maojiawei
 */
public class Boss {

    public void commandCheckNumber(TeamLeader teamLeader) {
        teamLeader.checkNumberOfCourses();
    }

}
