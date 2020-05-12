
给定多组 SKU, 给出设计方案, 算出所有属性可选的值, 
和当多个属性同时被选择的时候, 计算出价格区间及哪些属性是不可再选择的

1.
[
    {id: 1, properties: 
            [   
                {id: 1, name: color, value: red},
                {id: 2, name: size, value: 60},
                {id: 3, name: style, value: man}
            ]
    },
    {id: 2, properties: 
            [
                {id: 1, name: color, value: red},
                {id: 5, name: size, value: 70},
                {id: 3, name: style, value: man}
            ]
    },
    {id: 3, properties: 
            [
                {id: 7, name: color, value: red},
                {id: 8, name: size, value: 80},
                {id: 3, name: style, value: man}
            ]
    },
    {id: 4, properties: 
            [
                {id: 10, name: color, value: yellow},
                {id: 2, name: size, value: 60},
                {id: 3, name: style, value: man}
            ]
    },
    {id: 5, properties: 
            [
                {id: 10, name: color, value: yellow},
                {id: 5, name: size, value: 70},
                {id: 15, name: style, value: women}
            ]
    },
    {id: 6, properties: 
            [
                {id: 16, name: color, value: black},
                {id: 2, name: size, value: 60},
                {id: 3, name: style, value: man}
            ]
    },
    {id: 7, properties: 
            [
                {id: 16, name: color, value: black},
                {id: 5, name: size, value: 70},
                {id: 3, name: style, value: man}
            ]
    },
    {id: 8, properties: 
            [
                {id: 16, name: color, value: black},
                {id: 5, name: size, value: 70},
                {id: 15, name: style, value: women}
            ]
    }
]
答案：
color: [red, yellow, black]
size: [60, 70 ,80]
style: [man, women]

解题思路：

1. 给出所有可选的属性并不难. 创建一个 Map, 
Key 为 属性名称, value 为属性对象的 List
循环所有的 SKU, 然后循环内部的 properties 
根据 name 为 key, 放到对应的 List 中即可. 
如果想控制属性的先后顺序, 可以在属性对象中
增加一个 sort 字段, 用于排序即可

2. 当如上面的答案所示, 初期的展示效果就是如此. 
当用户开始点击每一个属性后面的各个值的时候, 比如:
当用户第一个点击 yellow 的时候, 80 这个值就应该是灰色的,
因为 yellow 和 80 的组合是没有的. 
总而言之就是当用户选择了一个属性之后, 
已经选择好的 N 个属性的值, 和还没选择的每一个值要查询一下, 
如果组合是不存在的就让那个值灰色不可选