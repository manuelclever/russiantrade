package eu.russiantrade.database.readAndWrite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import eu.russiantrade.api.comtrade.parser.TradeData;

import java.util.Arrays;
import java.util.List;

public class TradeMapper {

    /*
        Json format:
        data: {
            labels: [
                'January',
                'February',
                'March',
                'April',
                'May',
                'June'],
        datasets: [
            {
                label: 'My First dataset',
                backgroundColor: ['rgb(205,92,92)'],
                borderColor: 'rgb(255, 99, 132)',
                data: [2, 10, 5, 2, 20, 30, 45]
            }
        ]
        }
     */

    public static String jsonTradeDataOneTimePeriod(List<TradeData> tradeDataList) {
        ObjectMapper mapper = new ObjectMapper();

        String[] labelsArr = new String[tradeDataList.size()];
        long[] dataArr = new long[tradeDataList.size()];

        for(int i = 0; i < tradeDataList.size(); i++) {
            TradeData tradeData = tradeDataList.get(i);
            labelsArr[i] = tradeData.getCommodityCode() + " - " + tradeData.getCommodityDesc();
            dataArr[i] = tradeData.getTradeValue();
        }

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

        return outerData.toPrettyString();
    }

    public static String jsonTradeDataMonth(List<TradeData> tradeDataList) {
        return null;
    }

    public static String jsonTradeDataYears(List<TradeData> tradeDataList) {
        ObjectMapper mapper = new ObjectMapper();

        String[] labels = new String[]{
                "January",
                "February",
                "March",
                "April",
                "May",
                "June",
                "July",
                "September",
                "October",
                "November",
                "December"};

        ObjectNode data = mapper.createObjectNode();
        return null;
    }
}
