<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <title>Welcome to Pet Paradise</title>
  <style>
    @import url('https://fonts.googleapis.com/css2?family=Quicksand:wght@400;600&display=swap');

    body {
      font-family: 'Quicksand', sans-serif;
      display: flex;
      justify-content: center;
      align-items: center;
      height: 100vh;
      margin: 0;
      background: linear-gradient(135deg, #A5D6E2, #F0F8FF);
      color: #333;
    }

    .welcome-box {
      background: #fff;
      padding: 40px 30px;
      border-radius: 25px;
      text-align: center;
      box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
      border: 3px dashed #B3EBF2;
      max-width: 420px;
      transition: transform 0.3s ease;
    }

    .welcome-box:hover {
      transform: scale(1.02);
    }

    h1 {
      font-size: 2.3rem;
      margin-bottom: 20px;
      color: #34558B;
    }

    .username {
      font-weight: 600;
      color: #088697;
    }

    .pet-icon {
      width: 110px;
      height: 110px;
      margin: 0 auto 20px;
      background: url('https://cdn-icons-png.flaticon.com/512/616/616408.png') no-repeat center center;
      background-size: contain;
      border-radius: 50%;
      border: 4px solid #B3EBF2;
    }

    .welcome-message {
      font-size: 1.1rem;
      margin-bottom: 25px;
    }

    .cta-button {
      padding: 12px 22px;
      background: #34558B;
      border: none;
      border-radius: 10px;
      color: #fff;
      font-size: 1rem;
      cursor: pointer;
      transition: background 0.3s ease, transform 0.2s ease;
      text-decoration: none;
      display: inline-block;
      margin: 10px 8px;
    }

    .cta-button:hover {
      background: #088697;
      transform: translateY(-2px);
    }

    .footer-text {
      margin-top: 20px;
      font-size: 0.95rem;
      color: #666;
    }
  </style>
</head>
<body>
  <div class="welcome-box">
    <div class="pet-icon"></div>
    <h1>Welcome, <span class="username"><%= request.getParameter("username") %></span>!</h1>
    <p class="welcome-message">We?re thrilled to have you join our pet-loving community! ?</p>
    <a href="landingpage.html" class="cta-button">Home</a>
    <a href="profile.html" class="cta-button">Your Profile</a>
    <p class="footer-text">Together, let's make the world a better place for pets!</p>
  </div>
</body>
</html>