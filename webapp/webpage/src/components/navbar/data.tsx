import ua_flag from "./../../resources/images/flag_ukraine.png";
import placeholder from "./../../resources/images/svg_flags/002-oman.svg"

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
	{ value: 'Albania',label: (
		<div className='label'>
			<img src={placeholder} alt='Albania_flag' style={style.img}/>
			<span>Albania</span>
		</div>
	)},
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