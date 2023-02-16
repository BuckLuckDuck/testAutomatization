(function (){
    const enumChildren = (el)=>{
        const res = [];
        let e = el.firstElementChild;
        function getOffset(el) {
            const rect = el.getBoundingClientRect();
            return {
                left: rect.left + window.scrollX,
                top: rect.top + window.scrollY
            };
        }
        while (!!e) {
            res.push({
                tagName: e.tagName,
                x: getOffset(e).left,
                y: getOffset(e).top,
                width: e.clientWidth,
                height: e.clientHeight,
                children: enumChildren(e)});
            e = e.nextElementSibling;
        }
        return res;
    };
    return JSON.stringify({children: enumChildren(document)});
})()