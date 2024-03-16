package com.github.theword.constant;

public class ConfigConstantMessage extends BaseConstant {
    public static final String CONFIG_NOT_EXIST = PREFIX + "配置文件不存在，已自动创建！";
    public static final String CONFIG_COPY_FIELD = PREFIX + "生成配置文件失败";
    public static final String CONFIG_NO_STRING_IN_WEBSOCKET_URL_LIST = PREFIX + "在 websocket_url_list 中未找到 String 类型的 ";

    public static final String CONFIG_WEBSOCKET_URL_LIST_ERROR_USE_DEFAULT = PREFIX + "配置文件中 websocket_url_list 配置错误，使用默认配置！";

    public static final String CONFIG_READING_ERROR = PREFIX + "配置文件读取错误，使用默认配置！";

}
