import {notification} from "antd";

export const showNotification = ({type, description, duration = 4.5, title, ...props}) => {
  if (type) {
    notification[type]({description, duration, title, ...props});
  }
};