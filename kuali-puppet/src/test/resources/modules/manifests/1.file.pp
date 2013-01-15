 file {'/tmp/helloworld.txt':
    path    => '/tmp/helloworld.txt',
    ensure  => present,
    mode    => 0644,
    content => "Hello World",
 }