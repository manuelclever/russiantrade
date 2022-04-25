import {isChrome} from "react-device-detect";

export default function Dropdown() {

    if(isChrome) {
        return (
            <div className="dropdown bootstrap-select">
                <Select/>
            </div>
        );
    } else {
        return (
            <Select/>
        );
    }
}

function Select() {
    return (
        <select id="mySelect" className="selectpicker" title="countries" data-live-search="true">
            <optgroup label="association">
                <option data-content="<img style='margin-right:10px' src={ua_flag} alt='flag_ukraine' width='25' height='25'>NATO">NATO</option>
                <option data-content="<img style='margin-right:10px' src={ua_flag} alt='flag_ukraine' width='25' height='25'>EU">EU</option>
            </optgroup>
            <optgroup id="myGroup" label="countries">
                <option id="myItem" data-content="<img style='margin-right:10px' src={ua_flag} alt='flag_ukraine' width='25' height='25'>Germany">Germany</option>
                <option data-content="<img style='margin-right:10px' src={ua_flag} alt='flag_ukraine' width='25' height='25'>Italy">Italy</option>
            </optgroup>
        </select>
    );
}