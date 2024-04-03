import { AppBar, Box, Toolbar, Typography } from '@mui/material';
import { PieChart } from '@mui/x-charts/PieChart';
import React from 'react';

const drawerWidth = 240;

type TableStyles = {
  th: React.CSSProperties;
  td: React.CSSProperties;
  evenRow: React.CSSProperties;
};

export const Dashboard = () => {
  const containerStyle: React.CSSProperties = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    height: '50vh', // Adjust the height as needed
  };

  const tableData = [
    { id: 1, name: 'Product A', quantity: 30 },
    { id: 2, name: 'Product B', quantity: 25 },
    { id: 3, name: 'Product C', quantity: 35 },
    // Add more data as needed
  ];

  const tstyles: TableStyles = {
    th: {
      border: '1px solid black',
      textAlign: 'left',
      padding: '8px',
    },
    td: {
      border: '1px solid black',
      textAlign: 'left',
      padding: '8px',
    },
    // Define a separate CSS class for even rows
    evenRow: {
      backgroundColor: '#f2f2f2',
    },
  };

  return (
    <>
      <Box component="main" sx={{ flexGrow: 1, bgcolor: 'background.default', p: 3 }}>
        {/* <Toolbar /> */}
        <AppBar position="fixed" sx={{ width: `calc(100% - ${drawerWidth}px)`, ml: `${drawerWidth}px` }}>
          <Toolbar>
            {/* <Typography variant="h6" noWrap component="div">
              Dashboard
            </Typography> */}
          </Toolbar>
        </AppBar>
        <Typography variant="h4" noWrap component="div">
          Dashboard
        </Typography>
        <div style={containerStyle}>
          <PieChart
            series={[
              {
                data: [
                  { id: 0, value: 10, label: 'Product A' },
                  { id: 1, value: 15, label: 'Product B' },
                  { id: 2, value: 20, label: 'Product C' },
                ],
              },
            ]}
            width={400}
            height={200}
          />
        </div>

        {/* Table for item details */}
        <table style={{ borderCollapse: 'collapse', width: '100%' }}>
          <thead>
            <tr>
              <th style={tstyles.th}>ID</th>
              <th style={tstyles.th}>Product Name</th>
              {/* Add more headers/columns as needed */}
              <th style={tstyles.th}>Quantity</th>
            </tr>
          </thead>
          <tbody>
            {tableData.map((row, index) => (
              <tr key={row.id} style={index % 2 === 0 ? tstyles.evenRow : {}}>
                <td style={tstyles.td}>{row.id}</td>
                <td style={tstyles.td}>{row.name}</td>
                {/* Render more columns here */}
                <td style={tstyles.td}>{row.quantity}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </Box>
    </>
  );
};
export default Dashboard;
