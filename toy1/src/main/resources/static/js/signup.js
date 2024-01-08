window.addEventListener("load", function() {
	
	/* ========================================= */
    let form = this.document.querySelector("form");
    let keyBtn = form.querySelector(".key-button");
    let chkBtn = form.querySelector(".chk-button");
    let submitBtn = form.querySelector(".submit-button");

    let name = form.querySelector("input[name='username']");
    let email = form.querySelector("input[name='email']");
    
    let authKey = "";
    let chkKeyBox = form.querySelector(".key-chk");
    
    let isChecked = false;
    let isAuthorized = false;
    /* ========================================= */

	// 인증번호 메일 발송 후 저장
    keyBtn.onclick = async (e) => {
        e.preventDefault();
        this.alert("인증번호가 메일로 발송되었습니다.");

        console.log(name.value);
        console.log(email.value);

        let em = email.value;
        let n = name.value;

        let response = await fetch(`/api/send-email/${em}/${n}`, {method:"POST"});
        authKey = await response.text();
        console.log("인증번호 값: " + authKey);
    }
    
    // 1. 인증번호 확인
    chkBtn.onclick = async (e) => {
		e.preventDefault();
		
		let chkKey = chkKeyBox.value;
		console.log("인증확인 입력값: " + chkKey);
		if(chkKey != authKey){
			console.log("인증번호가 일치하지 않습니다.");
			isChecked = true;
			return;
		}
		
		// chkKey가 authKey와 일치하는 경우에만 인증확인 true
		isAuthorized = true;
		isChecked = true;
		console.log(isAuthorized);
	}
	
	// 2. 인증번호 확인란 변경 생기면 재인증 필요
	chkKeyBox.oninput = () => {
		isAuthorized = false;
		isChecked = false;
	}
	
	
	// 3. 제출버튼 클릭
	submitBtn.onclick = (e) => {
		
		// 인증확인 버튼 클릭 안하면 alert 띄우기 
		if(!isChecked){
			e.preventDefault();
			this.alert("인증 확인버튼을 눌러주세요.");
			return;			
		}
		
		// 인증확인 안되면 제출 안되게 막기
		if(!isAuthorized){
			e.preventDefault();
			this.alert("인증 확인이 되지 않았습니다.");
			return;
		}
		
		console.log("인증확인됨!");
		submitBtn.click();
	}
});
