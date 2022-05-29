import { Option, associationOptions, countryOptions } from './DropdownData';

import { GroupBase } from 'react-select';

const dropdownOptions: GroupBase<Option>[] = [
{
    label: "association",
    options: associationOptions,
},
{
    label: "country",
    options: countryOptions
}
];

export default dropdownOptions;