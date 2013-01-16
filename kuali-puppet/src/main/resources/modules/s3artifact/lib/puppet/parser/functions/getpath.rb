module Puppet::Parser::Functions
  newfunction(:getpath, :type => :rvalue, :doc => <<-EOS
    Replace any dots in the string passed in with underscores
    EOS
             ) do |args|
    mystring = args[0] # the string to convert
    tokens = mystring.split('.')
    for i in 0..5
    end
    return mystring
  end
end
