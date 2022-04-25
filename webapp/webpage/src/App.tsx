import { ChartConfiguration, ChartType, ChartData, ChartOptions } from 'chart.js';
import 'chart.js/auto';

import Navbar from './components/navbar/Navbar';
import WorldMap from './components/worldMap';

import img_bar from "./resources/images/bar.png";
import img_pie from "./resources/images/pie.png";

export interface myCustomChartConfig {
  type: ChartType,
  data: ChartData,
  options?: ChartOptions
  width?: number,
  height?: number
}

function App(this: any) {

  var config: ChartConfiguration = {
    type: "bar",
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
    },
    options: {},
  }

  const styles = {
    rebecca: {
      backgroundColor: 'rebeccapurple'
    },
    hundred: {
        height: '100%',
        width: '100%'
    },
    hundredAuto: {
        height: 'auto',
        width: '100%'
    },
    burly: {
        backgroundColor: 'burlywood'
    },
    text: {
        textAlign: 'center'
    }
  };

  return (
    // <div>
    //   <Test/>
    //   <Doughnut type={config.type} data={config.data}/>
    // </div>
    // <!-- options need to be in one row each, else selector doesn't work-->

    // <!--Panzoom https://timmywil.com/panzoom/-->
    // <!--https://commons.wikimedia.org/wiki/File_talk:BlankMap-World.svg/Documentation#Territories_included-->
    // <!--<?xml version="1.0" encoding="UTF-8"?>-->
    <div>
      <Navbar/>
      <div className="container">
          <div className="row firstRow">
              <div className="col-md exportCol">
                  <div className="row export">
                      <h1>Export</h1>
                  </div>
                  <div className="row overview-bar">
                      <div className="col " style={styles.rebecca}>
                          <img src={img_bar} style={styles.hundred}/>
                      </div>
                  </div>
                  <div className="row specific-pie">
                      <div className="col" style={styles.burly}>
                          <img src={img_pie} style={styles.hundred}/>
                      </div>
                  </div>
              </div>
              <div className="col-md importCol">
                  <div className="row import">
                      <h1>Import</h1>
                  </div>
                  <div className="row overview-bar">
                      <div className="col" style={styles.burly}>
                          <img src={img_bar} style={styles.hundred}/>
                      </div>
                  </div>
                  <div className="row specific-pie">
                      <div className="col" style={styles.burly}>
                          <img src={img_pie} style={styles.hundred}/>
                      </div>
                  </div>
              </div>
          </div>
          <div className="row">
              <div className="col"> 
                  <p id="curCountry" data-toggle="tooltip" data-placement="top" title="Tooltip">World</p>
              </div>
          </div>
          <WorldMap/>
      </div>
            
    </div>

    
  );
}

export default App;
