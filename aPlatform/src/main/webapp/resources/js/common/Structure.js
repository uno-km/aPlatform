/**
 * 
 */
class Builder
{
	init() 
	{
    	Object.keys(this).forEach((key) => {
			const setterName	=	`set${key.substr(0, 1).toUpperCase()}${key.substr(1)}`;
			this[setterName] = (value) => {
				this[key] = value;
        		return this;
      		};
    	});
  	}
	build() 
	{
		return this;
	}
}
