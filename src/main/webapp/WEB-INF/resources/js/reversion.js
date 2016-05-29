/**
 * Created by luopotaotao on 2016/4/18.
 */

$(function () {
    bindHandlers();
    bindT2Handlers();
    $.extend({
        reversion: {
            loadRentBillInfo: loadRentBillInfo,
            query: query
        }
    });
    var t1Url = 'reversion';
    var t2Url = 'reversionDtl'

    function bindHandlers() {
        $('a.easyui-linkbutton').unbind();
        // loadDic();
        $('#btn_add').bind('click', toAdd);
        $('#btn_edit').bind('click', toEdit);
        $('#btn_query').bind('click', query);
        $('#btn_remove').bind('click', remove);
        $('#btn_finish').bind('click', finish);
        $('#btn_unfinish').bind('click', unfinish);

        $('#btn_edit_save').bind('click', save);
        $('#btn_edit_close').bind('click', closeEditPanel);
    }


    function bindT2Handlers() {
        $('#btn_t2_edit').bind('click', t2ToEdit);
        $('#btn_t2_query').bind('click', t2Query);

        $('#btn_t2_edit_save').bind('click', t2Save);
        $('#btn_t2_edit_close').bind('click', t2CloseEditPanel);
    }

    function toAdd() {

        setEditable(true);
        loadAvailableRentBillNos(function () {
            $('#editForm').form('clear');
            $('#editPanel').dialog('open');
            $('#rentBillNo').combobox({disabled:false});

            $('#t2_dg').datagrid('loadData', {total: 0, rows: []});
        });


    }

    function formatDate(obj, names) {
        function format(name) {
            return (obj && obj[name]) ? new Date(obj[name]).format("yyyy-MM-dd") : null;
        }

        for (var i in names) {
            obj[names[i]] = format(names[i]);
        }
    }

    function toEdit() {
        var rows = $('#dg').datagrid('getChecked');
        if (rows) {
            if (rows.length > 1) {
                $.messager.alert('系统提示!', '只能对一行进行编辑!')
            } else if (rows.length == 1) {
                // loadAvailableRentBillNos();
                var row = rows[0];

                formatDate(row, ['beginDate', 'endDate','reversionDate', 'create_time', 'update_time']);
                $('#editForm').form('load', row);
                setEditable(row.billStat != 1);//已完成状态不可编辑
                t2Query();
                $('#editPanel').dialog('open');
                $('#rentBillNo').combobox({disabled:true});
                $('#rentBillNo').combobox('setValue',row.rentBillNo);

            }
        } else {
            $.messager.alert('系统提示!', '请选择要编辑的行!')
        }
    }

    function setEditable(flag) {
        if (flag) {
            $('#btn_edit_save').linkbutton('enable');
            $('#t2_menu a').linkbutton('enable');
            bindHandlers();
            bindT2Handlers();
        } else {
            $('#btn_edit_save').linkbutton('disable');
            $('#btn_edit_save').unbind('click');
            $('#t2_menu a').linkbutton('disable');
            $('#t2_menu').unbind('click');
        }
    }

    function remove() {
        var rows = $('#dg').datagrid('getChecked');
        var ids = [];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                ids.push(v['id']);
            });
        }
        if (ids.length > 0) {
            $.ajax({
                url: t1Url + '/delete',
                data: {ids: ids},
                dataType: 'json',
                type: 'post'
            }).success(function (ret) {
                if (ret && ret.flag > 0) {
                    $.messager.alert('系统提示!', '删除成功!');
                    query();
                } else {
                    $.messager.alert('系统提示!', '删除失败!请重新尝试或联系管理员!');
                }
            }).error(function (err) {
                $.messager.alert('系统提示!', '删除失败!请重新尝试或联系管理员!');
            });
        }
    }

    function query() {
        $('#dg').datagrid('reload',{'billStat': $('#query_billStat').combobox('getValues')});
    }

    function save() {
        if (!$('#editForm').form('validate')) {
            $.messager.alert('系统提示!', '请修正表单中有问题的字段!');
            return;
        }
        var isDetailsAvailable = true;
        var rows = $('#t2_dg').datagrid('getRows');
        $.each(rows, function (i, row) {
            if (!$.isNumeric(row['reversionAmount'])) {
                isDetailsAvailable = false;
                return false;
            }
        });
        if (!isDetailsAvailable) {
            $.messager.alert('系统提示!', '请修正明细中标红的行!');
            return;
        }

        var reversion = {};
        $('#editForm input').each(function (i, val) {
            var name = $(val).attr('name');
            if (name && $(val).val()) {
                reversion[name] = $(val).val();
            }
        });

        var details = $('#t2_dg').datagrid('getChanges', 'updated');
        if (details && details.length > 0) {
            reversion.details = JSON.stringify(details);
        }

        $('#editPanel').loading('保存中,请稍后...');
        $.ajax({
            url: t1Url + '/save',
            type: 'post',
            data: reversion
        }).success(function (ret) {
            if (ret && ret.flag) {
                $.messager.alert('系统提示!', '保存成功!');
                //如果是修改的话不关闭当前页面,新增的话才关闭
                if ($('#id').val()) {
                    $.get(t1Url + '/findById', {id: ('#id').val()}, function (data) {
                        formatDate(data, ['beginDate', 'endDate', 'create_time', 'update_time']);
                        $('#editForm').form('load', data);
                    });
                    query();
                    t2Query();
                } else {
                    $('#editForm').form('clear');
                    $('#editPanel').dialog('close');
                }

                query();
            } else {
                $.messager.alert('系统提示!', '保存失败,请重新尝试或联系管理员!');
            }
        }).error(function (e) {
            $.messager.alert('系统提示!', '保存失败,请重新尝试或联系管理员!');
        }).complete(function (e) {
            $('#editPanel').loaded();
        });


    }

    function finish() {
        $('#btn_finish').unbind();
        setTimeout(function () {
            $('#btn_finish').bind(finish);
        }, 1000);
        var rows = $('#dg').datagrid('getChecked');
        var ids = [];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                if (v['stat'] ==0) {
                    ids.push(v['id']);
                }
            });
        }
        if (ids && ids.length > 0) {
            $.messager.confirm('确认', '出租单确认是不可逆流程，是否确认审核？', function (r) {
                if (r) {
                    $.ajax({
                        url: t1Url + '/finish',
                        type: 'post',
                        dataType: 'json',
                        data: {ids: ids}
                    }).success(function (ret) {
                        if (ret && ret.flag) {
                            $.messager.alert('系统提示', '审核完成!');
                            query();
                        } else {
                            $.messager.alert('系统提示', '操作失败,请重新尝试或联系管理员!');
                        }
                    }).error(function (e) {
                        $.messager.alert('系统提示', '操作失败,请重新尝试或联系管理员!');
                    });
                }
            });

        } else {
            $.messager.alert('系统提示', '请选择未完成的条目进行审核!');
        }
    }

    function unfinish() {
        var rows = $('#dg').datagrid('getChecked');
        var ids = [];
        var warehouseIds=[];
        if (rows && rows.length > 0) {
            $(rows).each(function (i, v, r) {
                if (v['stat'] ==1) {
                    ids.push(v['id']);
                    warehouseIds.push(v['warehouseId']);
                }
            });
        }
        if (ids && ids.length > 0) {
            $.messager.confirm('确认', '出租单确认是不可逆流程，是否确认审核？', function (r) {
                if (r) {
                    $.ajax({
                        url: t1Url + '/unfinish',
                        type: 'post',
                        dataType: 'json',
                        data: {ids: ids,warehouseIds:warehouseIds}
                    }).success(function (ret) {
                        if (ret && ret.flag) {
                            $.messager.alert('系统提示', '审核完成!');
                            query();
                        } else {
                            $.messager.alert('系统提示', '操作失败,请重新尝试或联系管理员!');
                        }
                    }).error(function (e) {
                        $.messager.alert('系统提示', '操作失败,请重新尝试或联系管理员!');
                    });
                }
            });

        } else {
            $.messager.alert('系统提示', '请选择未完成的条目进行审核!');
        }
    }


    function t2ToEdit() {
        var rows = $('#t2_dg').datagrid('getChecked');
        if (rows) {
            if (rows.length > 1) {
                $.messager.alert('系统提示!', '只能对一行进行编辑!')
            } else if (rows.length == 1) {
                $('#t2EditForm').form('clear');
                $('#t2EditForm').form('load', rows[0]);
                $('#t2EditPanel').dialog('open');
                loadSkuImage(rows[0]['skuId']);
                
            }
        } else {
            $.messager.alert('系统提示!', '请选择要编辑的行!')
        }
    }
    function loadSkuImage(skuId){
        $.ajax({
            url:'image/findById',
            type:'get',
            data:{id:skuId},
            dataType:'json'
        }).success(function (ret) {
            if($.isPlainObject(ret)){
                if(ret.suffix){
                    $('#skuImage').attr('src', 'resources/images/upload/' + skuId + ret.suffix);
                }
            }
        });
    }

    function t2Save() {
        if ($('#t2EditForm').form('validate')) {
            var row = $('#t2_dg').datagrid('getChecked')[0];
            var index = $('#t2_dg').datagrid('getRowIndex', row);
            var item = {};
            if (item) {
                $('#t2EditForm input').each(function (i, val) {
                    var key = $(val).attr('name');
                    if (key) {
                        item[key] = $(val).val();
                    }
                });
                $('#t2_dg').datagrid('updateRow', {index: index, row: item});
            }
            calcTotal();
            $('#t2EditForm').form('clear');
            $('#t2EditPanel').dialog('close');
        } else {
            $.messager.alert('系统提示!', '请修正表单中有问题的字段!!');
        }
    }

    function calcTotal() {
        var rows = $('#t2_dg').datagrid('getRows');
        var compensateMoney = null;
        if (rows && rows.length) {
            compensateMoney = 0;
            $.each(rows, function (i, row) {
                var itemCompensate = parseFloat(row['itemCompensate']);
                compensateMoney += (isNaN(itemCompensate) ? 0 : itemCompensate);
            });

        }
        $('#compensateMoney').numberbox('setValue', compensateMoney);
    }


    function t2Query() {
        var url = null;
        if($('#id').val()){
            url = t2Url + '/findRentDtlsById?reversionId=' + $('#id').val();
        }else{
            url = t2Url + '/loadRentDtlsForReversion?rentId=' + $('#id').val();
        }
        $.ajax({
            url: url,
            dataType: 'json',
            type: 'get'
        }).success(function (ret) {
            if (ret && ret.rows) {
                $('#t2_dg').datagrid('loadData', ret);
            } else {
                $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
            }
        }).error(function (err) {
            $.messager.alert('系统提示!', '获取数据失败!请重新尝试或联系管理员!');
        });
    }

    function closeEditPanel() {
        $('#editPanel').dialog('close');
    }

    function t2CloseEditPanel() {
        $('#t2EditPanel').dialog('close');
    }

    function loadAvailableRentBillNos(callback) {
        $('#editPanel').loading('加载可用出租单信息,请稍后...');
        $.ajax({
            url: 'reversion/loadAvailableRentBillNos',
            type: 'get',
            dataType: 'json'
        }).success(function (ret) {
            if (ret ) {
                if( ret.length > 0){
                    $('#rentBillNo').combobox({
                        required: true,
                        valueField: 'id',
                        textField: 'billNo',
                        data:ret,
                        onSelect: function (rec) {
                            loadRentBillInfo(rec.id);
                        }
                    });
                    if(typeof callback=='function'){
                        callback();
                    }
                }else{
                    $.messager.alert('系统提示','没有已审核的出租单,无法新建归还单!');
                }

            }
        }).error(function (e) {
            $.messager.alert('系统提示', '加载出租单据信息失败,请刷新页面重新尝试或联系管理员!');
        }).complete(function () {
            $('#editPanel').loaded();
        });
    }

    function loadRentBillInfo(rentId) {
        if (rentId) {
            $('#editPanel').loading('加载所选出租单信息中,请稍后...');
            $.ajax({
                url: 'reversion/loadReversionFromRent',
                type: 'get',
                data: {rentId: rentId},
                dataType: 'json'
            }).success(function (ret) {
                if (ret && ret.rentBillId) {
                    formatDate(ret, ['beginDate', 'endDate']);
                    $('#editForm').form('load', ret);
                    loadRentBillDtlInfo(ret.rentBillId)
                }
            }).error(function (e) {
                $.messager.alert('系统提示', '加载所选出租单信息失败,请刷新页面重新尝试或联系管理员!');
            }).complete(function () {
                $('#editPanel').loaded();
            });
        }
    }

    function loadRentBillDtlInfo(rentId) {
        if (rentId) {
            $('#editPanel').loading('加载出租单明细信息中,请稍后...');
            $.ajax({
                url: 'reversionDtl/'+($('#id').val()?'findRentDtlsById':'loadRentDtlsForReversion'),
                type: 'get',
                data: $('#id').val()?{reversionId:$('#id').val()}:{rentId: rentId},
                dataType: 'json'
            }).success(function (ret) {
                if (ret && ret.rows) {
                    $('#t2_dg').datagrid('loadData', ret);
                }
            }).error(function (e) {
                $.messager.alert('系统提示', '加载出租明细信息失败,请刷新页面重新尝试或联系管理员!');
            }).complete(function () {
                $('#editPanel').loaded();
            });
        }
    }
})
;