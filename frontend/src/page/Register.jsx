import RegisterForm from "../components/RegisterForm";
import { redirect, useLocation } from "react-router-dom";
export default function Register() {
  return <RegisterForm />;
}

export async function action({ request }) {
  const data = await request.formData();

  const profileType = data.get("profileType");

  const payload = {
    name: data.get("firstName"),
    surname: data.get("lastName"),
    userName: data.get("userName"),
    oib: data.get("oib"),
    agencyName: data.get("agencyName"),
    email: data.get("email"),
    password: data.get("password"),
    phone: data.get("phone"),
    dateOfBirth: data.get("dateOfBirth"),
    cityId: data.get("cityId"),
    avatarUrl: data.get("avatarUrl") === "" ? "https://aurnchyhllskmomhcrxy.supabase.co/storage/v1/object/public/images/no-image-user.png" : data.get("avatarUrl"),
    profileType: profileType.toUpperCase()
  };

  const response = await fetch(
    `http://localhost:8080/api/auth/register/${profileType}`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },

      body: JSON.stringify(payload),
    },
  );

  if (response.status === 422 || response.status === 401) {
    return response;
  }
  if (!response.ok) {
    throw { message: "Something went wrong. Try again.", status: 500 };
  }
  /*
  const resData = await response.json();
  const token = resData.token;

  localStorage.setItem("token", token);
  const expiration = new Date();
  expiration.setHours(expiration.getHours() + 24);
  localStorage.setItem("expiration", expiration.toString());*/

  return redirect("/login");
}
