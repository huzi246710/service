package com.wwq.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * json工具类
 *
 * @author wangwq
 * @since 2019/07/23
 */
public final class JsonUtils {

    private JsonUtils() {
    }

    /**
     * json 非空检查
     *
     * @param paramJsonObject json对象
     * @param paramNameArry   检查字段的数组
     * @return String              异常信息
     */
    public static String notNullParmCheck(JSONObject paramJsonObject, String[] paramNameArry) {
        return notNullParmCheck("", paramJsonObject, paramNameArry);
    }

    /**
     * json 非空检查
     *
     * @param preParamName 参数名前缀
     * @param paramJsonObject json对象
     * @param paramNameArry   检查字段的数组
     * @return String              异常信息
     */
    public static String notNullParmCheck(String preParamName, JSONObject paramJsonObject, String[] paramNameArry) {
        StringBuilder errorSb = new StringBuilder();

        if (paramJsonObject != null && paramNameArry != null) {
            for (String paramName : paramNameArry) {
                String paramValue = paramJsonObject.getString(paramName);

                if (StringUtils.isBlank(paramValue)) {
                    errorSb.append(preParamName).append(paramName).append(" is null").append(",");
                }
            }
        }

        String errorMsg = errorSb.toString();
        int errorMsgLength = errorMsg.length();
        if (errorMsgLength == 0) {   //没有报错
            return null;
        } else {
            return errorMsg.substring(0, errorMsgLength - 1);
        }
    }

    /**
     * 把http请求参数封装到json对象中     (备注： 该接口的请求参数只有字符串)
     * (代码参考： https://www.cnblogs.com/cdf-opensource-007/p/6287780.html  )
     *
     * @param request 请求对象
     * @return json对象
     */
    public static JSONObject getHttpParameterJsonObject(HttpServletRequest request) {
        Map<String, String[]> properties = request.getParameterMap();       //把请求参数封装到Map<String, String[]>中
        JSONObject jsonObject = new JSONObject();

        for (Map.Entry<String, String[]> entry : properties.entrySet()) {
            String name = entry.getKey();
            String value = "";
            String[] valueArray = entry.getValue();

            if (valueArray != null) {
                int valueArrayLength = valueArray.length;

                if (valueArrayLength == 1) {
                    value = valueArray[0];//用于请求参数中请求参数名唯一
                } else if (valueArrayLength > 1) {
                    for (int i = 0; i < valueArrayLength; i++) { //用于请求参数中有多个相同名称
                        value = valueArray[i] + ",";
                    }
                    value = value.substring(0, valueArrayLength - 1);
                }
            }

            jsonObject.put(name, value);
        }

        return jsonObject;
    }

    /**
     * 将jsonObject中的参数拼接到url后面
     *
     * @param jsonTmp json对象
     * @param apiUrl  接口url
     * @return 拼接了参数的接口url
     */
    public static String getReqUrlFromJsonObject(JSONObject jsonTmp, String apiUrl) {
        StringBuilder paramSb = new StringBuilder();

        for (Map.Entry entry : jsonTmp.entrySet()) {
            paramSb.append(entry.getKey()).append("=").append(entry.getValue().toString()).append("&");
        }

        String paramStr = paramSb.toString();
        int paramStrLength = paramStr.length();
        if (paramStrLength == 0) {   //没有参数
            return apiUrl;
        } else {
            return apiUrl + "?" + paramSb.substring(0, paramStrLength - 1);
        }
    }

    /**
     * 校验字符串是否是json格式
     *
     * @param jsonInString 待校验的字符串
     * @return boolean       结果
     */
    public static boolean isJSONValid(String jsonInString) {
        try {
            JSONObject.parseObject(jsonInString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
