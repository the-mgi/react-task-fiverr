import React, {useEffect, useState} from 'react';
import {Button, Form, Input, message, Modal, Space, Table} from "antd";
import {DeleteOutlined, DownloadOutlined, EditOutlined} from '@ant-design/icons';
import {useSelector} from "react-redux";
import {API_STATUSES} from "../../shared/constants";
import {
  deleteCategory,
  getAllCategories,
  postBulkCategories,
  postNewCategory,
  updateCategory
} from "./category-table.service";

const {Item} = Form;

const CategoryTableComponent = () => {
  const categoriesState = useSelector((state) => state.categoryReducer);

  const [tableData, setTableData] = useState(categoriesState.allCategories);
  const [isModalVisible, setModalVisibility] = useState(false);
  const [confirmLoading, setConfirmLoading] = useState(false);
  const [newDataFlag, setNewDataFlag] = useState({categoryId: undefined});
  const [form] = Form.useForm();

  useEffect(() => {
    (async () => {
      await getAllCategories()
    })();
  }, []);

  useEffect(() => {
    setTableData(categoriesState.allCategories)
  }, [categoriesState.allCategories]);

  const CATEGORIES_COLUMNS = [
    {
      title: 'Category ID',
      dataIndex: 'categoryId',
      key: 'categoryId',
    },
    {
      title: 'Category Name',
      dataIndex: 'categoryName',
      key: 'categoryName',
    },
    {
      title: 'Inserted Timestamp',
      dataIndex: 'createdTimestamp',
      key: 'createdTimestamp',
    },
    {
      title: 'Last Updated Timestamp',
      dataIndex: 'lastUpdateTimestamp',
      key: 'lastUpdateTimeStamp',
    },
    {
      title: 'Actions',
      dataIndex: 'actions',
      render: (_, row) => {
        return (
          <Space>
            <DeleteOutlined style={{color: "red", fontSize: "18px"}} onClick={() => {
              (async () => {
                await deleteCategory(row.categoryId)
                await getAllCategories();
              })();
            }}/>
            <EditOutlined style={{color: "blue", fontSize: "18px"}} onClick={() => {
              form.setFieldsValue({"categoryName": row.categoryName});
              setNewDataFlag({categoryId: row.categoryId});
              setModalVisibility(true);
            }}/>
          </Space>
        );
      }
    }
  ];

  const getDataAndDownloadCsv = (tableId) => {
    const getDataInFormat = () => {
      const table = document.getElementById(tableId);
      if (table) {
        const rows = table.querySelectorAll("tr");
        const array = [];
        for (const row of rows) {
          const cellsRow = row.querySelectorAll("th,td");
          const list = [...cellsRow];
          array.push((list.slice(0, list.length - 1).map(element => element.innerText)).join(","))
        }
        return array.join("\n");
      }
    };

    const downloadFile = () => {
      const fileName = `${tableId}-${new Date().toDateString()}.csv`
      const requiredFormattedString = getDataInFormat();
      const linkTag = document.createElement("a");
      linkTag.setAttribute("href", `data:text/csv;charset=utf-8,${encodeURIComponent(requiredFormattedString)}`);
      linkTag.setAttribute("download", fileName);
      linkTag.style.display = "none";
      document.body.appendChild(linkTag);
      linkTag.click();
      document.body.removeChild(linkTag);
    };

    downloadFile();
  };

  const uploadCsv = (event) => {
    const file = event.target.files[0];
    if (file.type !== "text/csv") {
      message.error("Only CSV files allowed");
      event.target.value = "";
      return;
    }
    file.text()
      .then(response => {
        const array = response.split("\n");
        const resultingArray = [];
        if (array[0] === "Category ID,Category Name,Inserted Timestamp,Last Updated Timestamp") {
          const headNames = "categoryId,categoryName,insertedTimestamp,lastUpdateTimestamp".split(",");
          for (let i = 1; i < array.length; i++) {
            const values = array[i].split(",");
            const objToPush = {}
            for (let j = 0; j < headNames.length; j++) {
              objToPush[headNames[j]] = values[j]
            }
            resultingArray.push(objToPush);
          }
          (async () => {
            await postBulkCategories(resultingArray);
            await getAllCategories();
          })();
        } else {
          message.error("File not properly not formatted");
        }
      })

  };

  const handleSave = () => {
    console.log('newDataFlag.categoryId', newDataFlag.categoryId);
    const functionReq = newDataFlag.categoryId ? updateCategory : postNewCategory;
    form.submit();
    form.validateFields()
      .then(response => {
        setConfirmLoading(true)
        return functionReq({categoryName: response.categoryName}, newDataFlag.categoryId);
      })
      .then(() => {
        message.success("Category Added Successfully");
      })
      .catch(error => {
        message.error("Error Occurred while adding category")
      })
      .finally(() => {
        setConfirmLoading(false);
        getAllCategories();
        closeModal();
        setNewDataFlag({categoryId: undefined});
      });
  };

  const closeModal = () => {
    form.setFieldsValue({"categoryName": ""});
    setModalVisibility(false);
  };

  return (
    <div style={{width: "90%"}}>
      <div className="row" style={{justifyContent: "space-between"}}>
        <Button type="primary" onClick={() => {
          setModalVisibility(true)
        }}>
          Add new Category
        </Button>
        <div>
          <Space>
            <Button type="primary" onClick={() => getDataAndDownloadCsv("categoriesTable")}>
              Download CSV
              <DownloadOutlined style={{fontSize: "18px"}}/>
            </Button>
            <input accept=".csv" type="file" onChange={(event) => {
              uploadCsv(event)
            }}/>

          </Space>
        </div>
      </div>
      <Table
        id="categoriesTable"
        style={{width: "100%", margin: "var(--default-margin) 0"}}
        dataSource={tableData}
        columns={CATEGORIES_COLUMNS}
        loading={categoriesState.status === API_STATUSES.LOADING}
        pagination={{defaultPageSize: 10, showSizeChanger: true}}
      />
      <Modal
        title="Add New Category"
        visible={isModalVisible}
        onCancel={closeModal}
        width="400px"
        destroyOnClose={true}
        confirmLoading={confirmLoading}
        footer={[
          <Button form={form} htmlType="submit" key="submit" type="primary" onClick={handleSave}>Submit</Button>,
        ]}
      >
        <Form form={form}>
          <Item name="categoryName" rules={[{required: true, message: 'Category name cannot be empty'}]}>
            <Input placeholder="Category Name"/>
          </Item>
        </Form>
      </Modal>
    </div>
  );
};

export default CategoryTableComponent;