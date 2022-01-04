package com.mao.auto.code.client;

import com.mao.auto.code.config.PropertiesLoad;
import com.mao.auto.code.core.DB2DomainDto;
import com.mao.auto.code.core.DaoManager;
import com.mao.auto.code.core.Domain2Mapper;
import com.mao.auto.code.util.CommonUtils;
import com.mao.auto.code.util.StringConstants;

public class AutoCodeClient {

    public static void autoCode(String propertiesPath) {
        PropertiesLoad.init(propertiesPath);
        DB2DomainDto.genDomain();
        DB2DomainDto.genDto();
        Domain2Mapper.genMapper();
        DaoManager.genDao();
        DaoManager.genManager();
        CommonUtils.close(null, null, StringConstants.conn);
    }
}
