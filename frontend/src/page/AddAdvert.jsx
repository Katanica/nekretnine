import styles from "./css/AddAdvert.module.css";
import { redirect } from "react-router-dom";
import { getToken, getUserID } from "../api";
import AddAdvertForm from "../components/AddAdvertForm";

export default function AddAdvert() {
  return <AddAdvertForm />;
}

export async function action({ request }) {
  const data = await request.json();
  const token = getToken();
  const id = getUserID();
  console.log("ADD ADVERT " + JSON.stringify(data));

  const response = await fetch(
    `http://localhost:8080/api/advert/${id}`,
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      body: JSON.stringify(data),
    }
  );

  if (!response.ok) {
    throw new Response("Problem with adding advert", { status: 500 });
  }

  return redirect("/");
}