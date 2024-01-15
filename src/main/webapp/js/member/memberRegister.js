document.querySelector("#id").addEventListener('keyup', (e) => {
    const value = e.target.value;
    console.log(value);

    const guideOk = document.querySelector(".guide.ok");
    const guideError = document.querySelector(".guide.error");
    const idValid = document.querySelector("#idValid");

    if (/^\w{4,}$/.test(value)) {
        $.ajax({
            url : `${contextPath}/member/checkIdDuplicate`,
            data : {
                id : value
            },
            success(response) {
                const {result} = response;
                if (result) {
                    // 사용가능한 아이디인 경우
                    guideError.classList.add("hidden");
                    guideOk.classList.remove("hidden");
                    idValid.value = 1;
                } else {
                    // 이미 사용중인 아이디인 경우
                    guideOk.classList.add("hidden");
                    guideError.classList.remove("hidden");
                    idValid.value = 0;
                }
            }
        });
    } else {
        // 다시쓰기하는 경우
        guideOk.classList.add("hidden");
        guideError.classList.add("hidden");
        idValid.value = 0;
    }
});

const hobbyEtc = document.querySelector("#hobby-etc");
hobbyEtc.addEventListener('keyup', (e) => {
    // 엔터를 누른 경우, 입력완료로 간주한다.
    if (e.keyCode === 13) {
        // 자동으로 생겨난 <br><br>을 제거. blur처리
        // console.log(e.target.innerHTML); // 값<br><br>
        e.target.innerHTML = e.target.innerHTML.replace(/<br>/g, '');
        e.target.blur();
    }
});
hobbyEtc.addEventListener('blur', (e) => {
    const value = e.target.innerHTML;
    if (value && value != '직접입력') {
        const html = `
        <div class="inline-flex items-center mr-4">
                    <input id="hobby-${value}" type="checkbox" name="hobby" checked value="${value}" class="w-4 h-4 text-blue-600 bg-gray-100 border-gray-300 rounded focus:ring-blue-500 focus:ring-2" >
                    <label for="hobby-${value}" class="ms-2 text-sm font-medium text-gray-900">${value}</label>
                </div>`;
        /**
         * 특정요소기준으로 새요소 추가
         * - beforebegin 시작태그 앞. 이전형제요소로 추가
         * - afterbegin 시작태그 뒤. 첫 자식요소로 추가
         * - beforeend 종료태그 앞. 마지막 자식요소로 추가
         * - afterend 종료태그 뒤. 다음 형제요소로 추가
         * e.target.parentElement : label#hobby-etc를 감싼 div태그
         */
        e.target.parentElement.insertAdjacentHTML('beforebegin', html);
        e.target.innerHTML = '직접입력';
    }
});

/**
 * 회원가입 유효성검사
 */
document.memberRegisterFrm.addEventListener('submit', (e) => {
    const frm = e.target;
    const id = frm.id;
    const password = frm.password;
    const confirmPassword = document.querySelector("#confirm-password");
    const name = frm.name;
    const email = frm.email;
    const idValid = frm.idVaild;
    console.log(idValid);

    // 아이디 - 영문자/숫자 4글자 이상
    if (!/^\w{4,}$/.test(id.value)) {
        alert('아이디는 영문자/숫자 4글자 이상이어야 합니다.');
        e.preventDefault();
        return;
    }

    // 아이디 중복검사 통과여부
    if (idValid.value !== "1") {
        alert('사용가능한 아이디를 입력해주세요...');
        e.preventDefault();
        return;
    }

    // 비밀번호 - 영문자/숫자/특수문자(!@#$% 4글자 이상
    // const regexpPassword = /[a-z0-9!@#$%]{4,}/i;
    const regexpPassword = [
        {
            re: /[A-Za-z]/i,
            msg: '비밀번호는 영문자를 하나 이상 포함해주세요'
        }, {
            re: /\d/,
            msg: '비밀번호는 숫자 하나 이상 포함해주세요'
        }, {
            re: /[!@#$%]/,
            msg: '비밀번호는 특수문자(!@#$%)중에 하나를 포함해주세요'
        }, {
            re: /^.{4,}$/,
            msg: '비밀번호는 4글자 이상 작성해주세요'
        }];
    for(let i = 0; i < regExps.length; i++) {
        const {re, msg} = regexpPassword[i];
        if(!re.test(password.value)) {
            alert(msg);
            e.preventDefault();
            return;
        }
    }

    // 비밀번호 확인
    if (password.value !== confirmPassword.value) {
        alert('비밀번호가 같지 않습니다.');
        e.preventDefault();
        return;
    }
    
    // 이름 - 한글 2글자 이상
    if (!/^[가-힣]{2,}$/.test(name.value)) {
        alert('이름은 2글자 이상이어야 합니다.');
        e.preventDefault();
        return;
    }
    
    // 이메일 형식
    if (!/^[\w]{4,}@[\w]+(\.[\w]+){1,3}$/.test(email.value)) {
        alert('유효한 이메일을 작성하세요.');
        e.preventDefault();
        return;
    }
});
