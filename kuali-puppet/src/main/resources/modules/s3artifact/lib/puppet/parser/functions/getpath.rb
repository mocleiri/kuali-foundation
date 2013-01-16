module Puppet::Parser::Functions
  newfunction(:getpath, :type => :rvalue, :doc => <<-EOS
    Replace any dots in the string passed in with underscores
    EOS
             ) do |args|
    mystring = args[0] # the string to convert
    tokens = mystring.split('.')
    path = ''
    for i in 0..tokens.length
      path << tokens[i]
    end
    return mystring
  end
end
