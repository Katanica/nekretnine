import RegisterForm from "../components/RegisterForm";
import { redirect } from "react-router-dom";
export default function Register() {
  return <RegisterForm />;
}

export async function action({ request }) {
  const data = await request.formData();
  const registerData = {
    name: data.get("firstName"),
    surname: data.get("lastName"),
    userName: data.get("userName"),
    email: data.get("email"),
    password: data.get("password"),
    phone: data.get("phone"),
    dateOfBirth: data.get("dateOfBirth"),
    city: data.get("city"),
  };

  const response = await fetch("http://localhost:8080/api/auth/register/user", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(registerData),
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

  localStorage.setItem("token", token);
  const expiration = new Date();
  expiration.setHours(expiration.getHours() + 24);
  localStorage.setItem("expiration", expiration.toString());

  return redirect("/");
}
