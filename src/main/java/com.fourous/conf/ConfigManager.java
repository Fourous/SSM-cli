package com.fourous.conf;

import com.fourous.system.SystemConfig;
import com.google.gson.Gson;

public class ConfigManager {
    /**
     * 一般来说，配置文件可以通过xml json格式存储，可以持久化存储和传输，而且能够很方便读取出，最好配置文件放在统一文件夹中，以ConfigManager做中心配置
     * 如果还需要导入文件或者导出文件，可以通过txt pdf xlsx等主流文件存储，不过常用还是json格式
     */

    /**
     * 环境配置主要目录
     */
    public static final String ENV_CONFIG_ROOT_PATH = "conf";
    /**
     * 不同配置文件的名称
     */
    private final static String USER_PERMISSION = "\\userpermission.conf";

    /**
     * 通过配置文件的路径，得到文件后将内容转换成Java类实体
     */
    private static SystemConfig systemConfig;

    private static Object loadConfig(String Path,Class<? extends Object> clazz){
        String tempPath = ENV_CONFIG_ROOT_PATH+USER_PERMISSION;

    }




}
