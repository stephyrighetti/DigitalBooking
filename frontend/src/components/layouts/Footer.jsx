import { SocialMedia } from "../SocialMedia"
import "../../css/layouts/Footer.css"
import { useState } from "react";

export const Footer = () => {
  const [classNameIcons, setClassNameIcons] = useState("icons-social-media")
  return (
    <footer className="container-footer">
      <p className="footer-text">©2022 Digital Booking</p>
      <SocialMedia className={classNameIcons}/>
    </footer>
  );
};
