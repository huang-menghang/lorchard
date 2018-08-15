/** layui-v1.0.9_rls MIT License By http://www.layui.com */
;
layui.define("jquery",
function (e) {
    "use strict";
    var o = layui.jquery,
    a = layui.hint(),
    r = "layui-tree-enter",
    i = function (e) {
        this.options = e
    },
    t = {
        arrow: ["&#xe623;", "&#xe625;"],
        checkbox: ["&#xe626;", "&#xe627;"],
        radio: ["&#xe63f;", "&#xe643;"],
        branch: ["&#xe622;", "&#xe624;"],
        leaf: "&#xe621;"
    };
    i.prototype.init = function (e) {
        var o = this;
        e.addClass("layui-box layui-tree"),
        o.options.skin && e.addClass("layui-tree-skin-" + o.options.skin),
        o.tree(e),
        o.on(e)
    },
    i.prototype.tree = function (e, a) {
        var r = this,
        i = r.options,
        n = a || i.nodes;
        layui.each(n,
        function (a, n) {
            var l = n.children && n.children.length > 0,
            c = o('<ul class="' + (n.spread ? "layui-show" : "") + '"></ul>'),
            s = o(["<li " + (n.spread ? 'data-spread="' + n.spread + '"' : "") + ">",
            function () {
                return l ? '<i class="layui-icon layui-tree-spread">' + (n.spread ? t.arrow[1] : t.arrow[0]) + "</i>" : ""
            }(),
            function () {
                var checked = n.checked == undefined ? false : n.checked
                var checkbox = (true === checked ? t.checkbox[1] : t.checkbox[0])
                return i.check ? '<i class="layui-icon layui-tree-check" value=' + n.id + ' lay-id=' + n.id + ' layui-ckeck="' + n.checked + '">' + checkbox + "</i>" : ""
            }(),
            function () {
                return '<a href="' + (n.href || "javascript:;") + '" ' + (i.target && n.href ? 'target="' + i.target + '"' : "") + ">" + ('<i class="layui-icon layui-tree-' + (l ? "branch" : "leaf") + '">' + (l ? n.spread ? t.branch[1] : t.branch[0] : t.leaf) + "</i>") + ("<cite >" + (n.name || "未命名") + "</cite></a>")
            }(), "</li>"].join(""));
            l && (s.append(c), r.tree(c, n.children)),
            e.append(s),
            "function" == typeof i.click && r.click(s, n),
            r.spread(s, n),
            i.drag && r.drag(s, n)
            r.checkedClick(s, n)
        })
    },
      i.prototype.checkedClick = function (e, q) {
          var a = this,
          r = a.options;
          e.children(".layui-tree-check").on("click",
          function (e) {
              var checkbox = this;
              if (r.check == 'checkbox') {
                  var ckecked = (/^true$/i).test(checkbox.attributes["layui-ckeck"].value);
                  a.currentNode(checkbox, !ckecked);
                  a.childrenNode(checkbox, !ckecked);
              }
              var b = '';
              var rootNode = o(e.currentTarget).parents().find('.layui-tree');
              var lay = rootNode.find('.layui-tree-check[layui-ckeck=true]');
              layui.each(lay, function (i, v) {
                  var a = o(v).attr('lay-id');
                  b += a + ',';
              });
              if (b != '') {
                  b = b.substr(0, b.length - 1)
              }
              rootNode.data('treeChecked', b);
              if ("function" == typeof r.change) {
                  layui.stope(e),
           r.change(b);
              }

          })
      },
    i.prototype.currentNode = function (element, ck) {
        var a = this;
        var siblingsNode = o(element).parent().siblings().children('.layui-tree-check[layui-ckeck=' + ck + ']');
        var prtNode = o(element).parent().parent().children();
        //var childNode = o(element).siblings('ul').children('li');
        o(element).attr("layui-ckeck", ck);
        var s = ck == true ? 1 : 0;
        o(element).html(t.checkbox[s]);
        //a.childrenNode(childNode,ck);
        if (ck) {
            if (siblingsNode.length == 0) {
                a.parentNode(element, ck);
            }
        } else {
            if (siblingsNode.length == prtNode.length - 1) {
                a.parentNode(element, ck);
            }
        }


    },
      i.prototype.childrenNode = function (element, ck) {
          var a = this;
          var cldNode = o(element).siblings('ul').children('li').children('.layui-tree-check');
          if (cldNode.length > 0) {
              cldNode.attr("layui-ckeck", ck);
              var s = ck == true ? 1 : 0;
              cldNode.html(t.checkbox[s]);
              a.childrenNode(cldNode, ck)
          }
      },

     i.prototype.parentNode = function (element, ck) {

         var a = this;
         var prtNode = o(element).parent().parent().parent().children('.layui-tree-check');
         if (prtNode.length > 0) {
             a.currentNode(prtNode, ck);
         }
     },
    i.prototype.click = function (e, o) {
        var a = this,
        r = a.options;
        e.children("a").on("click",
        function (e) {
            layui.stope(e),
            r.click(o)
        })
    },
    i.prototype.spread = function (e, o) {
        var a = this,
        r = (a.options, e.children(".layui-tree-spread")),
        i = e.children("ul"),
        n = e.children("a"),
        l = function () {
            e.data("spread") ? (e.data("spread", null), i.removeClass("layui-show"), r.html(t.arrow[0]), n.find(".layui-icon").html(t.branch[0])) : (e.data("spread", !0), i.addClass("layui-show"), r.html(t.arrow[1]), n.find(".layui-icon").html(t.branch[1]))
        };
        i[0] && (r.on("click", l), n.on("dblclick", l))
    },
    i.prototype.on = function (e) {
        var a = this,
        i = a.options,
        t = "layui-tree-drag";
        e.find("i").on("selectstart",
        function (e) {
            return !1
        }),
        i.drag && o(document).on("mousemove",
        function (e) {
            var r = a.move;
            if (r.from) {
                var i = (r.to, o('<div class="layui-box ' + t + '"></div>'));
                e.preventDefault(),
                o("." + t)[0] || o("body").append(i);
                var n = o("." + t)[0] ? o("." + t) : i;
                n.addClass("layui-show").html(r.from.elem.children("a").html()),
                n.css({
                    left: e.pageX + 10,
                    top: e.pageY + 10
                })
            }
        }).on("mouseup",
        function () {
            var e = a.move;
            e.from && (e.from.elem.children("a").removeClass(r), e.to && e.to.elem.children("a").removeClass(r), a.move = {},
            o("." + t).remove())
        })
    },
    i.prototype.move = {},
    i.prototype.drag = function (e, a) {
        var i = this,
        t = (i.options, e.children("a")),
        n = function () {
            var t = o(this),
            n = i.move;
            n.from && (n.to = {
                item: a,
                elem: e
            },
            t.addClass(r))
        };
        t.on("mousedown",
        function () {
            var o = i.move;
            o.from = {
                item: a,
                elem: e
            }
        }),
        t.on("mouseenter", n).on("mousemove", n).on("mouseleave",
        function () {
            var e = o(this),
            a = i.move;
            a.from && (delete a.to, e.removeClass(r))
        })
    },
    e("tree",
    function (e) {
        var r = new i(e = e || {}),
        t = o(e.elem);
        return t[0] ? void r.init(t) : a.error("layui.tree 没有找到" + e.elem + "元素")
    })
});