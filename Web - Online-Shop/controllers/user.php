<?php

class User extends Controller {

    public function __construct() {
        parent::__construct();
    }

    public function index() {
        $data['title'] = 'Uebersicht';
        $data['products'] = $this->_model->all();

        $this->_view->render('header', $data);
        $this->_view->render('products/list', $data);
        $this->_view->render('footer');
    }

    public function add() {
        $data['title'] = 'Benutzerprofil anlegen';
        $data['form_header'] = 'Benutzerprofil anlegen';

        $this->_view->render('header', $data);
        $this->_view->render('products/form', $data);
        $this->_view->render('footer');
    }
  
    public function edit() {
        $data['title'] = 'Benutzerprofil editieren';
        $data['form_header'] = 'Benutzerprofil editieren';
        $id = filter_var(end($this->_url), FILTER_SANITIZE_NUMBER_INT);
        $data['product'] = $this->_model->product($id);
     
        $this->_view->render('header', $data);
        $this->_view->render('products/form', $data);
        $this->_view->render('footer');
    }

    public function insert() {
        $name = filter_var($_POST['name'], FILTER_SANITIZE_STRING);
        $url = filter_var($_POST['url'], FILTER_SANITIZE_URL);
        $image = filter_var($_POST['image'], FILTER_SANITIZE_URL);
        $price = filter_var($_POST['price'], FILTER_SANITIZE_NUMBER_INT);
        $postdata = array(
            'name' => $name,
            'url' => $url,
            'image' => $image,
            'price' => $price
        );
        $this->_model->insert($postdata);
        URL::redirect('index.php');
    }
    
    public function update() {
        $mail = filter_var($_POST['mail'], FILTER_SANITIZE_EMAIL);
        $password = filter_var($_POST['password'], FILTER_SANITIZE_STRING);
        $postdata = array(
            'name' => $name,
            'url' => $url
        );
        $this->_model->update($postdata, $id);
        URL::redirect('index.php');
    }
    
    public function delete($id) {      
        $this->_model->delete($id);
        
        URL::redirect('index.php');
    }
}
