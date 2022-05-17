(function(winbdow)
{
    'use Strict';
    function CommonRequest()
    {

    }
    CommonRequest.prototype.gfn_Init = function(targetFormId)
    {
    }
    CommonRequest.prototype.gfn_SetUrl = function(url){
        this.url = url;
    }
    CommonRequest.prototype.gfn_AddParam = function(key, value){
        var eAddParm =  document.createElement('input');
        eAddParm.setAttribute('type','hidden');
        eAddParm.setAttribute('id', key);
        eAddParm.setAttribute('name', key);
        eAddParm.setAttribute('value', value);

    }
    function CommonAjax(){

    }

    CommonAjax.prototype = new CommonRequest();

    CommonAjax.prototype.fn_SetCallBack = function(callback){
        this.callBack =  callback;
    }
    CommonAjax.prototype.fn_SetType = function(strType){
        this.type= strType;
    }
    CommonAjax.prototype.fn_Ajax = function(dtoHeader, reqyestDTO, async, optionValue){
        optionValue = Utils.gfn_IsNull(optionValue) ? null : optionValue;
        var objSendVO = {};
        if(!Utils.gfn_IsNull(dtoHeader)){
            objSendVO = dtoHeader;;
        }
        // if(!Utils.gfn_IsEmpty(Utis.get_GetSessionValue('inVO'))){
        //     objSendVO
        // }
        var callBack = this.callBack;
        var type = Utils.gfn_IsNull(this.type)===true ? 'POST' : this.type;
        this.type= '';
        var xmlHttpRequest =  new XMLHttpRequest();
        var url =  encodeURI(this.url);
        console.log(this.responseText);
        xmlHttpRequest.onreadystatechange = function(){
            if(this.readyState ==4){
                if(this.status ==200 || this.status ==201){
                    if(!Utils.gfn_IsNull(this.responseText)){
                        console.log(this.responseText);
                        var jsonResponse = JSON.parse(this.responseText.replace(/&~44;/gi,'&#44;'));
                        var processJson = optionValue == null ? Utils.GetUnitDTO(jsonResponse) : jsonResponse;
                        if(!Utils.gfn_IsNull(callBack)){
                            try{
                                callBack(processJson);
                            }
                            catch(e){
                                console.error(e);
                            }
                        }
                    }
                    else{
                        console.error("조회된 값이 없습니다. \n" + this.responseText);
                    }
                }
                else{
                    document.body.innerHTML = this.responseText;
                    console.error(this.responseText);
                }
            }
        };
        xmlHttpRequest.open(type, url, async);
        xmlHttpRequest.setRequestHeader('Content-type', 'application/json');
        xmlHttpRequest.send(encodeURIComponent(JSON.stringify(objSendVO)));
    }
    function CommonSubmit() {
        
    }
    window.CommonAjax =  new CommonAjax();
})(window)