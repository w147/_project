const s = new Set();

[2,3,5,4,5,2,2].forEach(x => s.add(x));
// Set结构不会添加重复的值

for(let i of s) {
  console.log(i);
}


// // ## 初始化
// // 例一 可以接受一个数组作为参数
// const set = new Set([1,2,3,4,4,]);

// // ...将一个数组转为用逗号分隔的参数序列
// console.log([...set]);

// // 例二
// const items = new Set([1,2,3,4,5,5,5,5,]);
// console.log(items.size);

// // set中NaN等于自身，其余比较相当于 ===
// let set3 = new Set();
// let a = NaN;
// let b = NaN;
// set3.add(a);
// set3.add(b);
// console.log(set3)

// // 两个对象总是不相等的
// let set4 = new Set();
// set4.add({});  // 1
// console.log(set4.size);

// set4.add({});  // 2
// console.log(set4.size);
