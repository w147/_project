let date = new Date(ms)
let nowDayOfWeek = date.getDay() - 1;
let nowDay = date.getDate(); 
let nowMonth = date.getMonth(); 
let nowYear = date.getFullYear(); 
let weekStartDate = new Date(nowYear, nowMonth, nowDay - nowDayOfWeek);
return weekStartDate