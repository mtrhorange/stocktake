import { AppBar, Box, Toolbar, Typography } from '@mui/material';
import React from 'react';

import { DataGrid, GridColDef, GridValueGetterParams } from '@mui/x-data-grid';
const drawerWidth = 240;

const columns: GridColDef[] = [
  { field: 'name', headerName: 'Name', width: 130 },
  { field: 'category', headerName: 'Category', width: 130 },
  { field: 'price', headerName: 'Price', width: 130 },
  { field: 'stock', headerName: 'Stock', width: 130 },
];

const rows = [
  { id: 1, name: 'Bagpack', price: 'SGD 25', stock: 5 },
  { id: 2, name: 'Book', price: 'SGD 15', stock: 2 },
  { id: 3, name: 'Sling bag', price: 'SGD 20', stock: 8 },
  { id: 4, name: 'Big bag', price: 'SGD 35', stock: 7 },
  { id: 5, name: 'Red Pen', price: 'SGD 2.50', stock: 2 },
  { id: 6, name: 'Green Pen', price: 'SGD 2', stock: 0 },
  { id: 7, name: 'Yellow Highlighter', price: 'SGD 2', stock: 7 },
  { id: 8, name: 'Eraser', price: 'SGD 2', stock: 3 },
  { id: 9, name: 'Pencil', price: 'SGD 3', stock: 0 },
];

export const Products = () => {
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
        <Typography variant="h4" noWrap component="div" style={{ marginBottom: '10px' }}>
          Products
        </Typography>
        <DataGrid
          rows={rows}
          columns={columns}
          initialState={{
            pagination: {
              paginationModel: { page: 0, pageSize: 10 },
            },
          }}
          pageSizeOptions={[5, 10, 25]}
          checkboxSelection
        />
        <div style={{ height: 600, width: '100%' }}></div>
      </Box>
    </>
  );
};
export default Products;
