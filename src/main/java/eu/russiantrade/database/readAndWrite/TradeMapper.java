package eu.russiantrade.database.readAndWrite;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import eu.russiantrade.api.comtrade.parser.TradeData;

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

    public String getTradeDataMonth(List<TradeData> tradeDataList) {
        ObjectMapper mapper = new ObjectMapper();

        String[] labels = new String[tradeDataList.size()];

        for(int i = 0; i < tradeDataList.size(); i++) {
            TradeData tradeData = tradeDataList.get(i);
            labels[i] = tradeData.getCommodityCode() + " - " + tradeData.getCommodityDesc();
        }


    }

    public String getTradeDataYear(List<TradeData> tradeDataList) {
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
