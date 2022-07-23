'use strict'

/** 画面load時の処理 */
jQuery(function ($) {
  /** 更新ボタンを押したときの処理 */
  $('#btn-update').click(function (event) {
    updateUser()
  })

  /** 削除ボタンを押したときの処理 */
  $('#btn-delete').click(function (event) {
    deleteUser()
  })

  function updateUser() {
    //formの値を取得
    var formData = $('#user-detail-form').serializeArray()

    $.ajax({
      type: 'PUT',
      cache: false,
      url: '/user/update',
      data: formData,
      dataType: 'json'
    })
      .done(function (data) {
        alert('ユーザーを更新しました')
        window.location.href = '/user/list'
      })
      .fail(function (jqXHR, textStatus, errorThrown) {
        // ajax失敗時の処理
        alert('ユーザー更新に失敗しました: ')
      })
      .always(function () {
        // 常に実行する処理
      })
  }

  function deleteUser() {
    //formの値を取得
    var formData = $('#user-detail-form').serializeArray()

    $.ajax({
      type: 'DELETE',
      cache: false,
      url: '/user/delete',
      data: formData,
      dataType: 'json'
    })
      .done(function (data) {
        alert('ユーザーを削除しました')
        window.location.href = '/user/list'
      })
      .fail(function (jqXHR, textStatus, errorThrown) {
        // ajax失敗時の処理
        alert('ユーザー削除に失敗しました')
      })
      .always(function () {
        // 常に実行する処理
      })
  }
})
