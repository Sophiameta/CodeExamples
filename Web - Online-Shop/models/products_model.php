<?php

class Products_Model extends Model {

   public function __construct(){
      parent::__construct();
   }

   /**
   * Gibt die letzten 20 Einträge im Archiv zurück.
   * @return array Liste aus Produkten mit id, timestamp, name, url, image und price
   */
   public function all() {
      return $this->_db->select('SELECT * FROM products ORDER BY id DESC LIMIT 0, 20');
   }
   
   public function product($id) {
      return $this->_db->select('SELECT * FROM products WHERE id =' . $id);
   }
   
   public function results($text) {
      return $this->_db->select("SELECT * FROM products WHERE name OR url LIKE'"."%$text%"."'");
   }

   public function insert($data) {
       
      $this->_db->insert($table = 'products', $data);
   }
   
   public function update($data, $id) {
      $where = "id =" . $id;
      $this->_db->update($table = 'products', $data, $where);
   }
   
   public function delete($id) {
      $where = "id =" . $id;
      $this->_db->delete($table = 'products', $where);
   }
   
   
}