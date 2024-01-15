// 페이지 로드시
const saveId = localStorage.getItem('saveId');
if (saveId) {
    document.querySelector("#id").value = saveId;
    document.querySelector("#saveId").checked = true;
}

// 로그인 폼 제출시 아이디 저장
// submit 버튼 클릭 -> submit 이벤트 -> submit 이벤트핸들러 호출! -> 제출!
document.memberLoginFrm.addEventListener("submit", (e) => {
    const saveId = e.target.saveId;
    const id = e.target.id;
    if (saveId.checked) {
        localStorage.setItem('saveId', id.value);
    } else {
        localStorage.removeItem('saveId');
    }
});