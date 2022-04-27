import 'chart.js/auto';
import { ChartConfiguration, ChartType, ChartData, ChartOptions } from 'chart.js';
import { Chart } from 'react-chartjs-2';


import Navbar from './components/navbar/Navbar';
import RenderWorldMap from './components/worldMap/RenderWorldMap';

export interface myCustomChartConfig {
  type: ChartType,
  data: ChartData,
  options?: ChartOptions
  width?: number,
  height?: number
}

function App(this: any) {

  var barConfig: ChartConfiguration = {
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
                backgroundColor: ['#6d597a', '#b56576', '#e56b6f', '#eaac8b'],
                borderColor: '#355070',
                data: [2, 10, 5, 2, 20, 30, 45] 
            }
      ]
    },
    options: {},
  }

  var doghnutConfig: ChartConfiguration = {
    type: "doughnut",
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
                backgroundColor: ['#6d597a', '#b56576', '#e56b6f', '#eaac8b'],
                borderColor: '#355070',
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
        height: '100%',
        width: 'auto'
    },
    burly: {
        backgroundColor: 'burlywood',
        height: '100%'
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
                            <Chart id="exportOverview" type={barConfig.type} data={barConfig.data}/>
                    </div>
                    <div className="row specific-pie">
                            <Chart id="exportSpecific" type={doghnutConfig.type} data={doghnutConfig.data}/>
                    </div>
                </div>
                <div className="col-md importCol">
                    <div className="row import">
                        <h1>Import</h1>
                    </div>
                    <div className="row overview-bar">
                            <Chart id="importOverview" type={barConfig.type} data={barConfig.data}/>
                    </div>
                    <div className="row specific-pie">
                            <Chart id="importSpecific" type={doghnutConfig.type} data={doghnutConfig.data}/>
                    </div>
                </div>
            </div>
            <div className="row">
                <div className="col"> 
                    <p id="curCountry" data-toggle="tooltip" data-placement="top" title="Tooltip">World</p>
                </div>
            </div>
            <div className="row">
                <div className="col" >
                    <RenderWorldMap/>
                </div>
            </div>
        </div>         
    </div>

    
  );
}

export default App;