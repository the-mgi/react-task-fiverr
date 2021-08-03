import React from "react";
import CategoryTableComponent from "../../components/category-table/category-table.component";
import "./home.styles.css";
import {ABOUT_PROJECT_DESC} from "../../shared/constants";

const TopDescription = () => {
  return (
    <div className="main-desc-container">
      <p>{ABOUT_PROJECT_DESC}</p>
    </div>
  );
};

const Home = () => {
  return (
    <div className="column center">
      <TopDescription/>
      <CategoryTableComponent/>
    </div>
  );
};

export default Home;