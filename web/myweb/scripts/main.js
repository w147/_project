// 切换图片
let myImage = document.querySelector('img');

myImage.onclick = function() {
    let mySrc = myImage.getAttribute("src")
    console.info(mySrc)
    if(mySrc == './images/1.jpg'){
        myImage.src = './images/2.jpg';
    }else if(mySrc == './images/2.jpg'){
        myImage.src = './images/3.jpg';
    }else if(mySrc == './images/3.jpg'){
        myImage.src = './images/4.gif';
    }else if(mySrc == './images/4.gif'){
        myImage.src = './images/1.jpg';
    }else{
        console.error("please check!")
    }
}

// 按钮事件
let myButton = document.querySelector('button');
let myHeading = document.querySelector('h1');

function setUserName() {
    let myName = prompt('请输入你的名字。');
    if(!myName || myName === null) {
        setUserName();
    } else {
        localStorage.setItem('name', myName);
        myHeading.innerHTML = 'Mozilla 酷毙了，' + myName;
    }
}

if(!localStorage.getItem('name')) {
    setUserName();
} else {
    let storedName = localStorage.getItem('name');
    myHeading.textContent = '你好，' + storedName;
}
myButton.onclick = function() {
    setUserName();
}