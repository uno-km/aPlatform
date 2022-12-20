/**
 * 
 */
(function(winbdow)
{
    'use Strict';
    function CommonAjax()
    {
    	this._url;
    	this._data;
    	this._async;
    	this._method;
    	this._callBack;
    	this._contentType;
    }
    CommonAjax.prototype	=	{
    		setUrl(url)
    		{
    			this._url	=	url;
    		}
    		,	setMethod(method)
    		{
				this._method	=	method;
    		}
    		,	setData(data)
    		{
    			this._data	=	data;
    		}
    		,	setAsync(async)
    		{
    			this._async	=	async;
    		}
    		,	setCallBack(callBack)
    		{
    			this._callBack	=	callBack;
    		}
    		,	setContentType(contentType)
    		{
    			this._contentType	=	contentType;
    		}
    		,excute()
    		{
    			let	outData	=	'';
    			if(!this._method)
    			{
					this._method	=	'GET';
    			}
    			else	if(this._method	===	'POST')
    			{
    				this._data	=	JSON.stringify(this._data);
    			}
    			if(!this._contentType)
    			{
    				this._contentType	=	'application/json; charset=utf-8';
    			}
    			if(!this._url)
    			{
    				this._url	=	"error/404"
    			}
    			if(!this._async)
    			{
    				this._async	=	false;
    			}
    			$.ajax({
    		        type	:	this._method
    		        ,	url	:	this._url
    		        ,	data	:	this._data
    		        ,	dataType	:	'JSON'
    		        ,	async	:	this._async
    		        ,	contentType	:	this._contentType 
    		        ,	success	:	function(data) 
    		        {
    		        	if(this._async)
    		        	{
    		        		if(this._callBack)
    		        		{
    		        			this._callBack(data);
    		        		}
    		        		else
    		        		{
    		        			return	data;
    		        		}
    		        	}
    		        	else
    		        	{
    		        		outData	=	data;
    		        	}
    		        }
    		        ,error: function (e) {
    		        	
    		            alert('통신실패!!');
    		        }
    		    });
    			if(!this._async)
    			{
    				if(this._callBack)
    				{
    					this._callBack(outData);
    				}
    				else
    				{
    					return	outData;
    				}
    			}
    		}
    		,flush()
    		{
    	    	this._url	=	undefined;
    	    	this._data	=	undefined;
    	    	this._async	=	undefined;
    	    	this._method	=	undefined;
    	    	this._callBack	=	undefined;
    	    	this._contentType	=	undefined;
    		}
    }
    window.CommonAjax =  new CommonAjax();
})(window)