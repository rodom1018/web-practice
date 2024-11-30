import logo from './logo.svg';
import './App.css';
import React, {useEffect, useState} from "react";

function App() {

  const handleGoogleLogin = (event) => {
    event.preventDefault();
    window.location.href = 'http://localhost:8080/oauth2/authorization/google';
  };
  
  const handleNaverLogin = (event) => {
    event.preventDefault();
    window.location.href = 'http://localhost:8080/oauth2/authorization/naver';
  };

  const handleKakaoLogin = (event) => {
    event.preventDefault();
    window.location.href = 'http://localhost:8080/oauth2/authorization/kakao';
  };

  const [data, setData] = useState('');

  const handleClick = () => {
    fetch('http://localhost:8080/account/sample/admin', {
      method: 'GET',
      credentials: 'include',
    })
      .then(response => response.text())
      .then(text => {
        setData(text);
      })
      .catch(error => {
        console.error('데이터 가져오기 오류:', error);
      });
  };  

  return (
  <>

    <h2>회원가입 Form</h2>

    <form id="joinForm">
      <label htmlFor="username">Username:</label>
      <input type="text" id="username" name="username" required></input><br></br><br></br>

      <label htmlFor="password">Password:</label>
      <input type="password" id="password" name="password" required></input><br></br><br></br>

      <button type="button" onClick={submitLogin}>Login</button>
    </form>
    <br></br><br></br>

    <h2>소셜 로그인</h2>
    <button onClick={handleNaverLogin}>
      네이버로 로그인
    </button>
    <br></br><br></br>

    <button onClick={handleGoogleLogin}>
      구글로 로그인
    </button>
    <br></br><br></br>

    <button onClick={handleKakaoLogin}>
      카카오로 로그인
    </button>
    <br></br><br></br>


    <h2>Login Form</h2>

    <form id="loginForm">
      <label htmlFor="username">Username:</label>
      <input type="text" id="username" name="username" required></input><br></br><br></br>

      <label htmlFor="password">Password:</label>
      <input type="password" id="password" name="password" required></input><br></br><br></br>

      <button type="button" onClick={submitForm}>Login</button>
    </form>
    <br></br><br></br>

    <h2>권한이 필요한 페이지에 접속 시도해보기</h2>
    <div>
    <button onClick={handleClick}>데이터 가져오기</button>
    <div>{data}</div>
    </div>
  </>
  );

  function submitLogin() {
    // Form data 수집
    const formData = new FormData();
    formData.append("username", document.getElementById("username").value);
    formData.append("password", document.getElementById("password").value);

    // 서버로 POST 요청 보내기
    fetch("http://localhost:8080/join", {
        method: "POST",
        credentials: "include",
        headers:{
            "Connection" : "keep-alive",
            "Accept" : "*/*",
            "User-Agent" : "PostmanRuntime/7.42.0"
        },
        body: formData
    })
    .then(response => {
        if (response.ok) {
            alert("회원가입 successful");
        } else {
            alert("회원가입 failed");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("An error occurred");
    });
  }

  function submitForm() {
    // Form data 수집
    const formData = new FormData();
    formData.append("username", document.getElementById("username").value);
    formData.append("password", document.getElementById("password").value);

    // 서버로 POST 요청 보내기
    fetch("http://localhost:8080/login", {
        method: "POST",
        credentials: "include",
        headers:{
            "Connection" : "keep-alive",
            "Accept" : "*/*",
            "User-Agent" : "PostmanRuntime/7.42.0"
        },
        body: formData
    })
    .then(response => {
        if (response.ok) {
            alert("Login successful");
        } else {
            alert("Login failed");
        }
    })
    .catch(error => {
        console.error("Error:", error);
        alert("An error occurred");
    });

    function displayCookies() {
    const cookies = document.cookie;
    document.getElementById("cookieInfo").innerHTML = "Stored Cookies: " + cookies;
    }

    // 페이지 로드 시 저장된 쿠키 표시
    window.onload = displayCookies;
  }
}

export default App;
