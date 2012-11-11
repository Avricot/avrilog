####Generating you own sign-certificate pkcs12 file :

First we create the root certificate. 
If you want to password-protect this key, add option -des3.
    openssl genrsa -out ca.key 4096

Next, we create our self-signed root CA certificate ca.crt.
You’ll need to provide an identity for your root CA:

    openssl req -new -x509 -days 1826 -key ca.key -out ca.crt

    You are about to be asked to enter information that will be incorporated
    into your certificate request.
    What you are about to enter is what is called a Distinguished Name or a DN.
    There are quite a few fields but you can leave some blank
    For some fields there will be a default value,
    If you enter '.', the field will be left blank.
    -----
    Country Name (2 letter code) [GB]:BE
    State or Province Name (full name) [Berkshire]:Brussels
    Locality Name (eg, city) [Newbury]:Brussels
    Organization Name (eg, company) [My Company Ltd]:https://DidierStevens.com
    Organizational Unit Name (eg, section) []:
    Common Name (eg, your name or your server's hostname) []:Didier Stevens (https://DidierStevens.com)
    Email Address []:didier stevens Google mail


The -x509 option is used for a self-signed certificate. 1826 days gives us a cert valid for 5 years.

Next step: create our subordinate CA that will be used for the actual signing. First, generate the key:

    openssl genrsa -des3 -out ia.key 4096
    
    Then, request a certificate for this subordinate CA:

    openssl req -new -key ia.key -out ia.csr
    
    You are about to be asked to enter information that will be incorporated
    into your certificate request.
    What you are about to enter is what is called a Distinguished Name or a DN.
    There are quite a few fields but you can leave some blank
    For some fields there will be a default value,
    If you enter '.', the field will be left blank.
    -----
    Country Name (2 letter code) [GB]:BE
    State or Province Name (full name) [Berkshire]:Brussels
    Locality Name (eg, city) [Newbury]:Brussels
    Organization Name (eg, company) [My Company Ltd]:https://DidierStevens.com
    Organizational Unit Name (eg, section) []:Didier Stevens Code Signing (https://DidierStevens.com)
    Common Name (eg, your name or your server's hostname) []:
    Email Address []:didier stevens Google mail
    
    Please enter the following 'extra' attributes
    to be sent with your certificate request
    A challenge password []:
    An optional company name []:
    
Next step: process the request for the subordinate CA certificate and get it signed by the root CA.

    opekilnssl x509 -req -days 730 -in ia.csr -CA ca.crt -CAkey ca.key -set_serial 01 -out ia.crt

    Signature ok
    subject=/C=FR/ST=Some-State/L=Paris/O=Avrilog/OU=avrilog/CN=avrilog
    Getting CA Private Key
    
   
The cert will be valid for 2 years (730 days) and I decided to choose my own serial number 01 for this cert (-set_serial 01). For the root CA, I let OpenSSL generate a random serial number.


You can next package the files in a pkcs12 :

    openssl pkcs12 -export -out ia.p12 -inkey ia.key -in ia.crt -chain -CAfile ca.crt
    
    Enter Export Password:
    Verifying - Enter Export Password:
    
    
more info here : http://blog.didierstevens.com/2008/12/30/howto-make-your-own-cert-with-openssl/


####Generating you own timestamp certificate file :

# generate CA (need to do it only once)
/usr/lib/ssl/misc/CA.sh -newca
# create certificate request
openssl req -new -keyout user.key -out user.req -config yourconf.cnf
# sign request by CA
openssl ca -policy policy_anything -config yourconf.cnf -out user.pem -infiles user.req
# convert it into PKCS#12 (pfx) container, that can be used from various soft
openssl pkcs12 -export -in user.pem -inkey user.key -out user.p12 -name user -caname your_ca_name -chain -CAfile ./demoCA/cacert.pem

