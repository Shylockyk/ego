package com.ego.item.service.impl;

import com.ego.commons.utils.JsonUtils;
import com.ego.dubbo.service.TbItemParamItemDubboService;
import com.ego.item.pojo.ParamItem;
import com.ego.item.service.TbItemParamItemService;
import com.ego.pojo.TbItemParamItem;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TbItemParamItemServiceImpl implements TbItemParamItemService {

    @Reference
    private TbItemParamItemDubboService tbItemParamItemDubboServiceImpl;

    @Override
    public String getParamItem(long itemId) {
        TbItemParamItem tbItemParamItem = tbItemParamItemDubboServiceImpl.selectByItemId(itemId);
        List<ParamItem> paramItems = JsonUtils.jsonToList(tbItemParamItem.getParamData(), ParamItem.class);


        StringBuilder sf = new StringBuilder();

        for (ParamItem param : Objects.requireNonNull(paramItems)) {
            sf.append("<table width='500' style='color:gray;'>");
            for (int i = 0 ;i<param.getParams().size();i++) {
                if(i==0){
                    sf.append("<tr>");
                    sf.append("<td align='right' width='30%'>").append(param.getGroup()).append("</td>");
                    sf.append("<td align='right' width='30%'>").append(param.getParams().get(i).getK()).append("</td>");
                    sf.append("<td>").append(param.getParams().get(i).getV()).append("</td>");
                    sf.append("<tr/>");
                }else{
                    sf.append("<tr>");
                    sf.append("<td> </td>");
                    sf.append("<td align='right'>").append(param.getParams().get(i).getK()).append("</td>");
                    sf.append("<td>").append(param.getParams().get(i).getV()).append("</td>");
                    sf.append("</tr>");
                }
            }
            sf.append("</table>");
            sf.append("<hr style='color:gray;'/>");
        }
        return sf.toString();
    }
}
