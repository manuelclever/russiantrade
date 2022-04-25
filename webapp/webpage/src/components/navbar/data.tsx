import ua_flag from "./../../resources/images/flag_ukraine.png";

export interface CountryOption {
    value: String;
    label: JSX.Element;
}

export interface GroupedOptions {
    label: String;
    options: CountryOption[];
}

const style = {
    img: {
        width: '1.7rem',
        height: '1rem',
        marginRight: '0.8rem'
    },
}

const associationOptions = [
    { value: 'nato', label: (
        <div className="label">
            <img src={ua_flag} alt="flag" style={style.img}/>
            <span>Nato</span>
        </div>
    )},
    { value: 'eu', label: (
        <div className="label">
            <img src={ua_flag} alt="flag" style={style.img}/>
            <span>EU</span>
    </div>
    ) }
  ];
  
const countryOptions: CountryOption[] = [
{ value: 'germany', label: (
    <div className="label">
        <img src={ua_flag} alt="flag" style={style.img}/>
        <span>Germany</span>
    </div>
) },
{ value: 'italy', label: (
    <div className="label">
        <img src={ua_flag} alt="flag" style={style.img}/>
        <span>Italy</span>
    </div>
) },
{ value: 'france', label: (
    <div className="label">
        <img src={ua_flag} alt="flag" style={style.img}/>
        <span>France</span>
    </div>
) }
];

const dropwdownOptions: GroupedOptions[] = [
{
    label: "association",
    options: associationOptions,
},
{
    label: "country",
    options: countryOptions
}
];

export default dropwdownOptions;