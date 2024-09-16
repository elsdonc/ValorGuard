import { useState, useEffect } from "react";
import "./App.css";

function App() {
  const [user, setUser] = useState(null);

  useEffect(() => {
    fetchUser();
    if (user) {
      console.log("logged in");
      window.location.href = "http://localhost:5173/dashboard";
    }
  }, []);

  const fetchUser = async () => {
    console.log("Fetching user");
    try {
      let response = await fetch(userEndpoint, {
        method: "GET",
        credentials: "include",
      });
      response = await response.json();
      setUser(response["name"]);
    } catch (err) {
      console.error(err);
    }
  };

  const handleGoogleSignIn = async () => {
    window.location.href = "http://localhost:8080/oauth2/authorization/google";
  };
  
  return <></>;
}

export default App;
