import React from "react";

function LandingPage({ handleSignIn, user }) {
  if (user != null) {
    window.location = "/dashboard";
  }
  return (
    <div>
      <h1 className={"headline"}>Reclaim Your Time</h1>
      <p className={"subheadline"}>
        Stay Accountable and Focus on What Matters
      </p>
      <button onClick={() => handleSignIn()} className={"cta"}>
        Break Your Addiction Now
      </button>
      <p className={"reassurance"}>
        Your details will not be shared with anyone.
      </p>
    </div>
  );
}

export default LandingPage;
