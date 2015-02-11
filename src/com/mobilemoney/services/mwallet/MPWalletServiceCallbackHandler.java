
/**
 * MPWalletServiceCallbackHandler.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.6.2  Built on : Apr 17, 2012 (05:33:49 IST)
 */

    package com.mobilemoney.services.mwallet;

    /**
     *  MPWalletServiceCallbackHandler Callback class, Users can extend this class and implement
     *  their own receiveResult and receiveError methods.
     */
    public abstract class MPWalletServiceCallbackHandler{



    protected Object clientData;

    /**
    * User can pass in any object that needs to be accessed once the NonBlocking
    * Web service call is finished and appropriate method of this CallBack is called.
    * @param clientData Object mechanism by which the user can pass in user data
    * that will be avilable at the time this callback is called.
    */
    public MPWalletServiceCallbackHandler(Object clientData){
        this.clientData = clientData;
    }

    /**
    * Please use this constructor if you don't want to set any clientData
    */
    public MPWalletServiceCallbackHandler(){
        this.clientData = null;
    }

    /**
     * Get the client data
     */

     public Object getClientData() {
        return clientData;
     }

        
           /**
            * auto generated Axis2 call back method for cashOutRequest method
            * override this method for handling normal response from cashOutRequest operation
            */
           public void receiveResultcashOutRequest(
                    com.mobilemoney.services.mwallet.MPWalletServiceStub.TransferResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cashOutRequest operation
           */
            public void receiveErrorcashOutRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for airtimeTopupRequest method
            * override this method for handling normal response from airtimeTopupRequest operation
            */
           public void receiveResultairtimeTopupRequest(
                    com.mobilemoney.services.mwallet.MPWalletServiceStub.TransferResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from airtimeTopupRequest operation
           */
            public void receiveErrorairtimeTopupRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for debitRequest method
            * override this method for handling normal response from debitRequest operation
            */
           public void receiveResultdebitRequest(
                    com.mobilemoney.services.mwallet.MPWalletServiceStub.TransferResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from debitRequest operation
           */
            public void receiveErrordebitRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for pingRequest method
            * override this method for handling normal response from pingRequest operation
            */
           public void receiveResultpingRequest(
                    com.mobilemoney.services.mwallet.MPWalletServiceStub.PingResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from pingRequest operation
           */
            public void receiveErrorpingRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for customerVerificationRequest method
            * override this method for handling normal response from customerVerificationRequest operation
            */
           public void receiveResultcustomerVerificationRequest(
                    com.mobilemoney.services.mwallet.MPWalletServiceStub.CustomerVerificationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from customerVerificationRequest operation
           */
            public void receiveErrorcustomerVerificationRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for transactionQueryRequest method
            * override this method for handling normal response from transactionQueryRequest operation
            */
           public void receiveResulttransactionQueryRequest(
                    com.mobilemoney.services.mwallet.MPWalletServiceStub.TransactionQueryResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from transactionQueryRequest operation
           */
            public void receiveErrortransactionQueryRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for customerRegistrationRequest method
            * override this method for handling normal response from customerRegistrationRequest operation
            */
           public void receiveResultcustomerRegistrationRequest(
                    com.mobilemoney.services.mwallet.MPWalletServiceStub.CustomerRegistrationResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from customerRegistrationRequest operation
           */
            public void receiveErrorcustomerRegistrationRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for reverseTxnRequest method
            * override this method for handling normal response from reverseTxnRequest operation
            */
           public void receiveResultreverseTxnRequest(
                    com.mobilemoney.services.mwallet.MPWalletServiceStub.ReverseTxnResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from reverseTxnRequest operation
           */
            public void receiveErrorreverseTxnRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for balanceGetRequest method
            * override this method for handling normal response from balanceGetRequest operation
            */
           public void receiveResultbalanceGetRequest(
                    com.mobilemoney.services.mwallet.MPWalletServiceStub.BalanceGetResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from balanceGetRequest operation
           */
            public void receiveErrorbalanceGetRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for purchaseRequest method
            * override this method for handling normal response from purchaseRequest operation
            */
           public void receiveResultpurchaseRequest(
                    com.mobilemoney.services.mwallet.MPWalletServiceStub.TransferResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from purchaseRequest operation
           */
            public void receiveErrorpurchaseRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for cashInRequest method
            * override this method for handling normal response from cashInRequest operation
            */
           public void receiveResultcashInRequest(
                    com.mobilemoney.services.mwallet.MPWalletServiceStub.TransferResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from cashInRequest operation
           */
            public void receiveErrorcashInRequest(java.lang.Exception e) {
            }
                
           /**
            * auto generated Axis2 call back method for creditRequest method
            * override this method for handling normal response from creditRequest operation
            */
           public void receiveResultcreditRequest(
                    com.mobilemoney.services.mwallet.MPWalletServiceStub.TransferResponse result
                        ) {
           }

          /**
           * auto generated Axis2 Error handler
           * override this method for handling error response from creditRequest operation
           */
            public void receiveErrorcreditRequest(java.lang.Exception e) {
            }
                


    }
    