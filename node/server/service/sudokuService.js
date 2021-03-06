let sudoku = []
let tmp = {}

module.exports = {
    tip: function(){
        for(let x = 1; x <= 9; x++){
            for(let y = 1; y <= 9; y++){
                let num = getByPos(x, y)
                if(num > 0) continue
                let nums = getValueRange(x, y)
                if(nums.length > 1){
                    let pos = x * 10 + y
                    tmp[pos] = nums
                }else if(nums.length == 1){
                    return {x, y, v:nums[0]}
                }else{
                    return {x:-1, y:-1, v:-1}
                }
            }
        }

        let pre = 0, next = 0 
        while(true){
            pre = Object.keys(tmp).length
            for(let pos in tmp){
                let x = ~~(pos / 10), y = pos % 10
                let nums = getValueRange(x, y)
                if(nums.length == 1){
                    return {x, y, v:nums[0]}
                }
            }
            next = Object.keys(tmp).length
            if(pre == next) break
        }
        return {x:-1, y:-1, v:-1}
    },
    crack: function (){
        for(let x = 1; x <= 9; x++){
            for(let y = 1; y <= 9; y++){
                let num = getByPos(x, y)
                if(num > 0) continue
                let nums = getValueRange(x, y)
                if(nums.length > 1){
                    let pos = x * 10 + y
                    tmp[pos] = nums
                }else if(nums.length == 1){
                    fillNum(x, y, nums[0])
                }else{
                    console.error("get value range error")
                    process.exit()
                }
            }
        }
    },

    dealTmp: function (){
        let pre = 0, next = 0 
        while(true){
            pre = Object.keys(tmp).length
            for(let pos in tmp){
                let x = ~~(pos / 10), y = pos % 10
                let nums = getValueRange(x, y)
                if(nums.length == 1){
                    fillNum(x, y, nums[0])
                    delete tmp[pos]
                }
            }
            next = Object.keys(tmp).length
            if(pre == next) break
        }
        return sudoku
    },

    init: function (data){
        sudoku = data
    }

}
function fillNum (x, y, val){
    sudoku[y-1][x-1] = val
}
function getByPos (x, y){
    if(Number.isInteger(x) && x > 0 && x < 10 && Number.isInteger(y) && y > 0 && y < 10){
        return sudoku[y-1][x-1]
    }
    console.error("get pos error")
    process.exit()
}

// 未确定的值，给出取值范围
function getValueRange (row, col){
    let exists = []
    // 取出行中每个值，进行对比
    for(let x = 1; x <= 9; x++){
        if(row == x) continue
        let x_col_num = getByPos(x, col)
        if(x_col_num == 0) continue
        exists.push(x_col_num)
    }
    for(let y = 1; y <= 9; y++){
        let row_y_num = getByPos(row, y)
        if(row_y_num == 0) continue
        exists.push(row_y_num)
    }
    for(let m = 0; m <= 2; m++){
        let col_offset = getOffset(col, m)
        for(let n = 0; n <= 2; n++){
            if(m == 0 && n == 0) continue
            let row_offset = getOffset(row, n)
            let val = getByPos(row_offset, col_offset)
            if(val != 0){
                exists.push(val)
            }
        }
    }
    // 求差集
    return diffSet(exists)
}

function getOffset (n, offset){
    let t = n + offset
    if(n >= 7 && n <= 9){
        if(t >= 10){
            t -= 3
        }
        return t
    }else if(n >= 4 && n <= 6){
        if(t >= 7){
            t -= 3
        }
        return t
    }else if(n >= 1 && n <= 3){
        if(t >= 4){
            t -= 3
        }
        return t
    }else{
        console.error("get pos error")
        process.exit()
    }
}

function diffSet(exists){
    let ranges = [1,2,3,4,5,6,7,8,9], subSet = []
    ranges.forEach(function(val) {
        if (!exists.includes(val)) {
            subSet.push(val)
        }
    });
    return subSet
}