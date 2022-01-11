const { string } = require("prop-types");

(function(window){
    'use strict';
    function Utils() {
        var day = new Date();
        this.date= new Date(day.getFullYear(),day.getMonth()+1);
        this.positionX = null;
        this.positionY = null;
        this.oEvnValue = {};
        this.pageData = {};
    }
    Utils.prototype = 
    {
        gfn_IsEmpty : function(o){
            for(var prop in o){
                if(o.hasOwnProperty(prop)){
                    return false;
                }
            }
            return true;
        },
        gfun_isNull : function(inputValue) {
            if(inputValue==null){
                return true;
            } 
            if(inputValue =='NaN'){
                return true;
            }
            if(inputValue == ' '){
                return true;
            }
            if(new String(inputValue).valueOf() == "undefined") {
                return true;
            }
            var chkString = new String(inputValue);
            if(chkString.valueOf()=="undefined"){
                return true;
            } 
            if(chkString == null){
                return true;
            }
            if(chkString.toString().length == 0){
                return true;
            }
        },
        gfn_GetUnitDTO : function(outVO){
            var ignoreDTO ={
                ordSeqNo : null
            }
            for(var DTO in outVO){
                if(!ignoreDTO.hasOwnProperty(DTO)){
                    return outVO[DTO];
                }
            }
        }
    }
    window.Utils = new Utils();
})(window);