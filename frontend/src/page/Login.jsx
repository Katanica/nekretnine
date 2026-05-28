import LoginForm from "../components/LoginForm";
import { redirect } from "react-router-dom";
function Login() {
  return <LoginForm />;
}

export default Login;

export async function action({ request }) {
  const data = await request.formData();
  const loginData = {
    email: data.get("email"),
    password: data.get("password"),
  };

  const response = await fetch("http://localhost:8080/api/auth/login", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(loginData),
  });

  if (response.status === 422 || response.status === 401) {
    return response;
  }

  if (!response.ok) {
    throw new Response(
      JSON.stringify({ message: "Something went wrong. Try again" }),
      { status: 500 },
    );
  }

  const resData = await response.json();
  const token = resData.token;
  const id = resData.id;
  localStorage.setItem("token", token);

  // dekodiranje tokena
  const payload = JSON.parse(atob(token.split(".")[1]));
  localStorage.setItem("id", payload.id);
  console.log(payload);
  console.log(token);
  console.log(payload.id);

  const expiration = new Date();
  expiration.setHours(expiration.getHours() + 1);
  localStorage.setItem("expiration", expiration.toISOString());

  return redirect("/");
}
