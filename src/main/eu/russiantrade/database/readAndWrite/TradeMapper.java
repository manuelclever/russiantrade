package eu.russiantrade.database.readAndWrite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import eu.russiantrade.api.comtrade.parser.TradeData;

import java.util.Arrays;
import java.util.List;

public class TradeMapper {

    public static String jsonCommodity(List<TradeData> tradeDataList) {
        if(tradeDataList != null) {
            String[] labelsArr = new String[tradeDataList.size()];
            long[] dataArr = new long[tradeDataList.size()];

            for (int i = 0; i < tradeDataList.size(); i++) {
                TradeData tradeData = tradeDataList.get(i);
                labelsArr[i] = tradeData.getCommodityCode() + " - " + tradeData.getCommodityDesc();
                dataArr[i] = tradeData.getPrimaryValue();
            }
            return map(labelsArr, dataArr);
        }
        return null;
    }

    private static String map(String[] labelsArr, long[] dataArr) {
        ObjectMapper mapper = new ObjectMapper();

        ObjectNode innerData = mapper.createObjectNode();
        innerData.put("label", "");
        ArrayNode dataArrNode = innerData.putArray("data");
        Arrays.stream(dataArr).forEach(dataArrNode::add);

        ObjectNode datasets = mapper.createObjectNode();
        ArrayNode arrayNode = datasets.putArray("datasets");
        arrayNode.add(innerData);

        ObjectNode outerData = mapper.createObjectNode();
        ArrayNode arrayNodeLabels = outerData.putArray("labels");
        ArrayNode arrayNodeDatasets = outerData.putArray("datasets");
        Arrays.stream(labelsArr).forEach(arrayNodeLabels::add);
        arrayNodeDatasets.add(innerData);

        return outerData.toString();
    }

    public static String jsonTotal(List<TradeData> tradeDataList) {
        if(tradeDataList != null) {
            String[] labelsArr = new String[tradeDataList.size()];
            long[] dataArr = new long[tradeDataList.size()];

            for (int i = 0; i < tradeDataList.size(); i++) {
                labelsArr[i] = Integer.toString(tradeDataList.get(i).getRefPeriodId());
                dataArr[i] = tradeDataList.get(i).getPrimaryValue();
            }
            return map(labelsArr, dataArr);
        }
        return null;
    }
}
